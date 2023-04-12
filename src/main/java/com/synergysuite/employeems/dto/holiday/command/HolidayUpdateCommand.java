package com.synergysuite.employeems.dto.holiday.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayUpdateCommand {
    @NotNull(message = "Id can't be null.")
    private Integer id;
    private String reason;
    @NotNull(message = "Date from can't be null.")
    private LocalDate dateFrom;
    @NotNull(message = "Date to can't be null.")
    private LocalDate dateTo;
    @NotNull(message = "Employee ID can't be null.")
    private Integer employeeId;
    private Boolean isApproved;

}
