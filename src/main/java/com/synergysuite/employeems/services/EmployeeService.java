package com.synergysuite.employeems.services;

import com.synergysuite.employeems.dto.employee.command.EmployeeCreateCommand;
import com.synergysuite.employeems.dto.employee.query.EmployeeAllInfoQuery;
import com.synergysuite.employeems.dto.employee.query.EmployeeQuery;
import com.synergysuite.employeems.entities.Employee;
import com.synergysuite.employeems.mappers.EmployeeMapper;
import com.synergysuite.employeems.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public Page<EmployeeQuery> findAll(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);

        return employees.map(employeeMapper::toEmployeeQuery);
    }

    public Page<EmployeeQuery> findByName(Pageable pageable, String firstName) {
        Page<Employee> employees = employeeRepository.findByName(pageable, firstName);

        return employees.map(employeeMapper::toEmployeeQuery);
    }

    public Integer add(EmployeeCreateCommand employeeCreateCommand) {
        Employee employee = employeeMapper.toEmployee(employeeCreateCommand);

        employeeRepository.save(employee);

        return employee.getId();
    }

    public void update(EmployeeCreateCommand employeeCreateCommand, Integer id) {
        Employee employee = employeeMapper.toEmployee(employeeCreateCommand);
        employee.setId(id);

        employeeRepository.save(employee);
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeAllInfoQuery findById(Integer id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        return employeeMapper.toEmployeeAllInfoQuery(employee);
    }
}
