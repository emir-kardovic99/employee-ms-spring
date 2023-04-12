package com.synergysuite.employeems.services;

import com.synergysuite.employeems.dto.holiday.command.HolidayCreateCommand;
import com.synergysuite.employeems.dto.holiday.command.HolidayUpdateCommand;
import com.synergysuite.employeems.dto.holiday.query.HolidayExtendQuery;
import com.synergysuite.employeems.dto.holiday.query.HolidayQuery;
import com.synergysuite.employeems.entities.Holiday;
import com.synergysuite.employeems.mappers.HolidayMapper;
import com.synergysuite.employeems.repositories.HolidayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HolidayService {

    private final HolidayRepository holidayRepository;
    private final HolidayMapper holidayMapper;

    public List<HolidayQuery> find(Integer id) {
        List<Holiday> holidays = holidayRepository.findByEmployeeId(id);

        return holidays.stream().map(holidayMapper::toHolidayQuery).toList();
    }

    public void add(HolidayCreateCommand holidayCreateCommand) {
        Holiday holiday = holidayMapper.toHoliday(holidayCreateCommand);

        holidayRepository.save(holiday);
    }

    public void update(HolidayUpdateCommand holidayCommand)
    {
        Holiday holiday = holidayMapper.toHoliday(holidayCommand);
        holidayRepository.save(holiday);
    }

    public void delete(Integer id) {
        holidayRepository.deleteById(id);
    }

    public List<HolidayExtendQuery> findAllUnapprovedHolidays()
    {
        return holidayRepository.findAllUnapproved();
    }
}
