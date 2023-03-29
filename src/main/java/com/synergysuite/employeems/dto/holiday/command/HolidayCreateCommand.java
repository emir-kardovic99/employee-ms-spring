package com.synergysuite.employeems.dto.holiday.command;

import com.synergysuite.employeems.dto.employee.query.EmployeeQuery;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayCreateCommand {

    private String reason;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private EmployeeQuery employee;

}
