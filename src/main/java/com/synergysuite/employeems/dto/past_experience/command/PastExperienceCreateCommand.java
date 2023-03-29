package com.synergysuite.employeems.dto.past_experience.command;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PastExperienceCreateCommand {

    private String name;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer employeeId;

}
