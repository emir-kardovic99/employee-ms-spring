package com.synergysuite.employeems.repositories;

import com.synergysuite.employeems.entities.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
    List<Holiday> findByEmployeeId(Integer id);
}
