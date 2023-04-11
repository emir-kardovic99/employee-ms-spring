package com.synergysuite.employeems.error.handlers;

import com.synergysuite.employeems.error.ValidationException;
import com.synergysuite.employeems.error.dto.ArgumentNotValidDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request)
    {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        return getErrorResponse(fieldErrors);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        List<FieldError> fieldErrors = ex.getErrors().getFieldErrors();
        return getErrorResponse(fieldErrors);
    }

    private ResponseEntity<Object> getErrorResponse(List<FieldError> fieldErrors) {
        List<ArgumentNotValidDTO> listOfErrors = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            ArgumentNotValidDTO dto = new ArgumentNotValidDTO();
            dto.setMessage(fieldError.getDefaultMessage());
            dto.setFieldName(fieldError.getField());

            listOfErrors.add(dto);
        }

        return new ResponseEntity<>(listOfErrors, HttpStatus.BAD_REQUEST);
    }
}
