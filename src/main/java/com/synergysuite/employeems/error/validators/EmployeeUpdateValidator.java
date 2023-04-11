package com.synergysuite.employeems.error.validators;

import com.synergysuite.employeems.dto.employee.command.EmployeeUpdateCommand;
import com.synergysuite.employeems.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class EmployeeUpdateValidator implements Validator {

    private final EmployeeService employeeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(EmployeeUpdateCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeUpdateCommand employeeCommand = (EmployeeUpdateCommand) target;
        validateUsername(employeeCommand, errors);
    }

    private void validateUsername(EmployeeUpdateCommand target, Errors errors) {
        Boolean usernameExist = employeeService.usernameUpdateExists(target);
        if (usernameExist) {
            errors.rejectValue(
                    "username",
                    "username.username-already-exists",
                    "Employee with username " + target.getUsername() + " already exists."
            );
        }
    }
}
