package com.synergysuite.employeems.mappers;

import com.synergysuite.employeems.dto.employee.command.EmployeeCreateCommand;
import com.synergysuite.employeems.dto.employee.query.EmployeeQuery;
import com.synergysuite.employeems.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface EmployeeMapper {

    EmployeeQuery toEmployeeQuery(Employee employee);
    Employee toEmployee(EmployeeCreateCommand employeeCommand);
}
