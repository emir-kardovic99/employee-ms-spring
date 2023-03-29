package com.synergysuite.employeems.dto.holiday.command;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayCreateCommand {

    private String reason;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer employeeId;

}
