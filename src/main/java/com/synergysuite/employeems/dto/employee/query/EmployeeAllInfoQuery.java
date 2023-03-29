package com.synergysuite.employeems.dto.employee.query;

import com.synergysuite.employeems.dto.holiday.query.HolidayQuery;
import com.synergysuite.employeems.dto.past_experience.query.PastExperienceQuery;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeAllInfoQuery {

    private Integer id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private LocalDate startDate;
    private List<PastExperienceQuery> pastExperiences;
    private List<HolidayQuery> holidays;

}
