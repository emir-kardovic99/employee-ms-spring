package com.synergysuite.employeems.mappers;

import com.synergysuite.employeems.dto.holiday.command.HolidayCreateCommand;
import com.synergysuite.employeems.dto.holiday.command.HolidayUpdateCommand;
import com.synergysuite.employeems.dto.holiday.query.HolidayQuery;
import com.synergysuite.employeems.entities.Holiday;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface HolidayMapper {

    Holiday toHoliday(HolidayCreateCommand holidayCreateCommand);
    Holiday toHoliday(HolidayUpdateCommand holidayUpdateCommand);
    HolidayQuery toHolidayQuery(Holiday holiday);

}
