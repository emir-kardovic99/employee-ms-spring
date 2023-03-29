package com.synergysuite.employeems.dto.holiday.query;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayQuery {

    private Integer id;
    private String reason;
    private LocalDate dateFrom;
    private LocalDate dateTo;

}
