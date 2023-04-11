package com.synergysuite.employeems.error.dto;

import lombok.Data;

@Data
public class ArgumentNotValidDTO {
    private String message;
    private String fieldName;
}
