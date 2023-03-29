package com.synergysuite.employeems.mappers;

import com.synergysuite.employeems.dto.holiday.command.HolidayCreateCommand;
import com.synergysuite.employeems.dto.holiday.query.HolidayQuery;
import com.synergysuite.employeems.entities.Holiday;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface HolidayMapper {
    Holiday toHoliday(HolidayCreateCommand holidayCreateCommand);
    HolidayQuery toHolidayQuery(Holiday holiday);

}
