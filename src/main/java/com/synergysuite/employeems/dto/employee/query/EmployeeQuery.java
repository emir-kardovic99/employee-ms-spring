package com.synergysuite.employeems.dto.employee.query;

import com.synergysuite.employeems.dto.holiday.query.HolidayQueryDTO;
import com.synergysuite.employeems.dto.past_experience.query.PastExperienceQueryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class EmployeeQuery {

    private Integer id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private LocalDate startDate;
    private List<PastExperienceQueryDTO> pastExperiences;
    private List<HolidayQueryDTO> holidays;

}
