package com.synergysuite.employeems.dto.employee.command;

import com.synergysuite.employeems.entities.Holiday;
import com.synergysuite.employeems.entities.PastExperience;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class EmployeeCreateCommand {

    private String firstName;
    private String lastName;
    private String jobTitle;
    private LocalDate startDate;
    private Set<PastExperience> pastExperiences;
    private Set<Holiday> holidays;

}
