package com.synergysuite.employeems.dto.employee.query;

import com.synergysuite.employeems.dto.holiday.query.HolidayQuery;
import com.synergysuite.employeems.dto.past_experience.query.PastExperienceQuery;
import com.synergysuite.employeems.dto.role.query.RoleQuery;
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
    private String username;
    private String password;

    private List<PastExperienceQuery> pastExperiences;
    private List<HolidayQuery> holidays;
    private List<RoleQuery> roles;

}
