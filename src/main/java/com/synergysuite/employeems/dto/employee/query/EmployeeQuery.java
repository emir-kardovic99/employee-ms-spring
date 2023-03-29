package com.synergysuite.employeems.dto.employee.query;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeQuery {

    private Integer id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private LocalDate startDate;

}
