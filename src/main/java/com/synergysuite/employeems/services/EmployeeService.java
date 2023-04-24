package com.synergysuite.employeems.services;

import com.synergysuite.employeems.dto.employee.command.EmployeeCreateCommand;
import com.synergysuite.employeems.dto.employee.command.EmployeeUpdateCommand;
import com.synergysuite.employeems.dto.employee.query.EmployeeAllInfoQuery;
import com.synergysuite.employeems.dto.employee.query.EmployeeQuery;
import com.synergysuite.employeems.dto.role.query.RoleQuery;
import com.synergysuite.employeems.entities.Employee;
import com.synergysuite.employeems.entities.Role;
import com.synergysuite.employeems.mappers.EmployeeMapper;
import com.synergysuite.employeems.repositories.EmployeeRepository;
import com.synergysuite.employeems.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<EmployeeQuery> findAll(Pageable pageable)
    {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.map(employeeMapper::toEmployeeQuery);
    }

    public Page<EmployeeQuery> findByName(Pageable pageable, String firstName)
    {
        Page<Employee> employees = employeeRepository.findByName(pageable, firstName);
        return employees.map(employeeMapper::toEmployeeQuery);
    }

    public Optional<Employee> findByUsernameWithRoles(String username)
    {
        return employeeRepository.findByUsernameWithRoles(username);
    }

    @CachePut(value = "employee", key = "#result")
    public Integer add(EmployeeCreateCommand employeeCreateCommand)
    {
        String password = passwordEncoder.encode(employeeCreateCommand.getPassword());
        employeeCreateCommand.setPassword(password);

        Employee employee = employeeMapper.toEmployee(employeeCreateCommand);
        Role role = roleRepository.findByName("ROLE_USER");

        employee.addRole(role);
        employeeRepository.save(employee);

        return employee.getId();
    }

    @CachePut(value = "employee", key = "#employeeCommand.id")
    public void update(EmployeeUpdateCommand employeeCommand)
    {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeCommand.getId());
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.copy(employeeCommand);
            employeeRepository.save(employee);
        }
    }

    @CacheEvict(value = "employee", key = "#id")
    public void delete(Integer id)
    {
        employeeRepository.deleteById(id);
    }

    public EmployeeAllInfoQuery findById(Integer id)
    {
        Employee employee = employeeRepository.findByIdWithRoles(id).orElse(null);
        return employeeMapper.toEmployeeAllInfoQuery(employee);
    }

    public void addRole(RoleQuery roleQuery, Integer id)
    {
        Employee employee = employeeRepository.findById(id).orElse(null);
        Role role = roleRepository.findById(roleQuery.getId()).orElse(null);
        if (employee != null && role != null)
        {
            employee.addRole(role);
            employeeRepository.save(employee);
        }
    }

    public Boolean usernameExists(String username)
    {
        return employeeRepository.existsByUsername(username);
    }

    public Boolean usernameUpdateExists(EmployeeUpdateCommand target)
    {
        return employeeRepository.existsByUsernameAndDifferentId(target.getUsername(), target.getId());
    }

    public EmployeeAllInfoQuery findByUsername(String username) {
        Employee employee = employeeRepository.findByUsernameWithRoles(username).orElse(null);
        return employeeMapper.toEmployeeAllInfoQuery(employee);
    }
}
