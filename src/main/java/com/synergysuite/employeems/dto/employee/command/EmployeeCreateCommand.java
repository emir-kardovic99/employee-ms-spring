package com.synergysuite.employeems.dto.employee.command;

import jakarta.validation.constraints.*;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeCreateCommand {

    @NotBlank(message = "First name can't be blank.")
    private String firstName;
    @NotBlank(message = "Last name can't be blank.")
    private String lastName;
    @NotBlank(message = "Job title can't be blank.")
    private String jobTitle;
    @NotNull(message = "Start date can't be null.")
    private LocalDate startDate;
    private LocalDate endDate;
    @NotEmpty(message = "Username can't be blank.")
    private String username;
    @NotNull(message = "Password can't be null.")
    private String password;

}
