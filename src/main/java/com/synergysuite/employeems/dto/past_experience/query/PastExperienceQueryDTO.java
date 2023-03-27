package com.synergysuite.employeems.dto.past_experience.query;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PastExperienceQueryDTO {

    private Integer id;
    private String name;
    private LocalDate dateFrom;
    private LocalDate dateTo;

}
