package com.synergysuite.employeems.dto.past_experience.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PastExperienceCreateCommand {

    @NotBlank(message = "Past experience name can't be blank.")
    private String name;
    @NotNull(message = "Date from can't be null.")
    private LocalDate dateFrom;
    @NotNull(message = "Date to can't be null.")
    private LocalDate dateTo;
    @NotNull(message = "Employee ID can't be null.")
    private Integer employeeId;

}
