package com.synergysuite.employeems.error.validators;

import com.synergysuite.employeems.dto.employee.command.EmployeeCreateCommand;
import com.synergysuite.employeems.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class EmployeeValidator implements Validator {

    public final EmployeeService employeeService;

    @Override
    public boolean supports(Class<?> clazz)
    {
        return clazz.isAssignableFrom(EmployeeCreateCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        EmployeeCreateCommand employee = (EmployeeCreateCommand) target;
        validateUsername(employee.getUsername(), errors);
    }

    private void validateUsername(String username, Errors errors)
    {
        Boolean usernameExists = employeeService.usernameExists(username);
        if (usernameExists) {
            errors.rejectValue(
                    "username",
                    "username.username-already-exists",
                    "Employee with username " + username + " already exists."
            );
        }
    }
}
