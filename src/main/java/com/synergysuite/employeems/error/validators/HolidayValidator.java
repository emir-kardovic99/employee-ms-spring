package com.synergysuite.employeems.error.validators;

import com.synergysuite.employeems.dto.holiday.command.HolidayCreateCommand;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class HolidayValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(HolidayCreateCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HolidayCreateCommand holiday = (HolidayCreateCommand) target;
        validateDates(holiday.getDateFrom(), holiday.getDateTo(), errors);
    }

    private void validateDates(LocalDate dateFrom, LocalDate dateTo, Errors errors) {
        if (dateFrom.isAfter(dateTo)) {
            errors.rejectValue(
                    "dateFrom",
                    "dateFrom.must-be-before",
                    "Date From must be after Date To."
            );
        }
    }
}
