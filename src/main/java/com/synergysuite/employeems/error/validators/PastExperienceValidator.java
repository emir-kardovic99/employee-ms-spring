package com.synergysuite.employeems.error.validators;

import com.synergysuite.employeems.dto.employee.command.EmployeeCreateCommand;
import com.synergysuite.employeems.dto.past_experience.command.PastExperienceCreateCommand;
import com.synergysuite.employeems.services.EmployeeService;
import com.synergysuite.employeems.services.PastExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class PastExperienceValidator implements Validator {

    private final PastExperienceService pastExperienceService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(PastExperienceCreateCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PastExperienceCreateCommand employee = (PastExperienceCreateCommand) target;
        validateDates(employee.getDateFrom(), employee.getDateTo(), errors);
    }

    private void validateDates(LocalDate dateFrom, LocalDate dateTo, Errors errors) {
        if (dateFrom.isAfter(dateTo)) {
            errors.rejectValue(
                    "dateFrom",
                    "dateFrom.must-be-before",
                    "Date from must be before Date to."
            );
        }
    }
}
