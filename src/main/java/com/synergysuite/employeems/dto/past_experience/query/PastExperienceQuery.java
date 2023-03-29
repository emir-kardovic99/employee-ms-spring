package com.synergysuite.employeems.dto.past_experience.query;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PastExperienceQuery {

    private Integer id;
    private String name;
    private LocalDate dateFrom;
    private LocalDate dateTo;

}
