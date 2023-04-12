package com.synergysuite.employeems.repositories;

import com.synergysuite.employeems.dto.holiday.query.HolidayExtendQuery;
import com.synergysuite.employeems.entities.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Integer>
{
    List<Holiday> findByEmployeeId(Integer id);

    @Query(value = """
            select new com.synergysuite.employeems.dto.holiday.query.HolidayExtendQuery
            (holiday.id, holiday.reason, holiday.dateFrom, holiday.dateTo,
            holiday.isApproved, employee.firstName, employee.lastName, holiday.employeeId)
            from Holiday holiday, Employee employee
            where holiday.employeeId = employee.id and holiday.isApproved = false
            """)
    List<HolidayExtendQuery> findAllUnapproved();
}
