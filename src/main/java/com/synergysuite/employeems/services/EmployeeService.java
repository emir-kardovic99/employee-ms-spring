package com.synergysuite.employeems.services;

import com.synergysuite.employeems.dto.employee.command.EmployeeCreateCommand;
import com.synergysuite.employeems.dto.employee.query.EmployeeQuery;
import com.synergysuite.employeems.entities.Employee;
import com.synergysuite.employeems.mappers.EmployeeMapper;
import com.synergysuite.employeems.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        Page<Employee> employees = employeeRepository.findByFirstName(pageable, firstName);

        return employees.map(employeeMapper::toEmployeeQuery);
    }

    public EmployeeQuery create(EmployeeCreateCommand employeeCreateCommand) {
        Employee employee = employeeMapper.toEmployee(employeeCreateCommand);

        employeeRepository.save(employee);

        return employeeMapper.toEmployeeQuery(employee);
    }

    public EmployeeQuery update(EmployeeCreateCommand employeeCreateCommand, Integer id) {
        Employee employee = employeeMapper.toEmployee(employeeCreateCommand);
        employee.setId(id);

        employeeRepository.save(employee);

        return employeeMapper.toEmployeeQuery(employee);

    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }
}
