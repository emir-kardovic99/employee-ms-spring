package com.synergysuite.employeems.dto.employee.command;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeCreateCommand {

    private String firstName;
    private String lastName;
    private String jobTitle;
    private LocalDate startDate;

}
