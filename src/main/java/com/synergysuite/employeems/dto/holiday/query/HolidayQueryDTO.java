package com.synergysuite.employeems.dto.holiday.query;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class HolidayQueryDTO {

    private Integer id;
    private String reason;
    private LocalDate dateFrom;
    private LocalDate dateTo;

}
