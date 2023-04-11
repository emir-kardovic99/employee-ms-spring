package com.synergysuite.employeems.error;

import lombok.Getter;
import org.springframework.validation.Errors;

@Getter
public class ValidationException extends Exception{

    private final Errors errors;

    public ValidationException(Errors errors) {
        super();
        this.errors = errors;
    }

}
