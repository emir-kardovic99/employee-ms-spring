package com.synergysuite.employeems.dto.employee.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeUpdateCommand {

    @NotNull(message = "Id can't be null.")
    private Integer id;
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

}
