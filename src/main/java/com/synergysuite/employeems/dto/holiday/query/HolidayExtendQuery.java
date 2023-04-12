package com.synergysuite.employeems.dto.holiday.query;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayExtendQuery {

    private Integer id;
    private String reason;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Boolean isApproved;
    private String firstName;
    private String lastName;
    private Integer employeeId;

    public HolidayExtendQuery(Integer id, String reason, LocalDate dateFrom, LocalDate dateTo,
                              Boolean isApproved, String firstName, String lastName, Integer employeeId) {
        this.id = id;
        this.reason = reason;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.isApproved = isApproved;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
    }
}
