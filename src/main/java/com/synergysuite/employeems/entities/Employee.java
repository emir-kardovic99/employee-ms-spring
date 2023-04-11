package com.synergysuite.employeems.entities;

import com.synergysuite.employeems.dto.employee.command.EmployeeCreateCommand;
import com.synergysuite.employeems.dto.employee.command.EmployeeUpdateCommand;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Column(unique = true)
    private String username;
    private String password;

    @OneToMany(
            mappedBy = "employeeId",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = PastExperience.class)
    @ToString.Exclude
    private List<PastExperience> pastExperiences = new ArrayList<>();

    @OneToMany(
            mappedBy = "employeeId",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = Holiday.class)
    @ToString.Exclude
    private List<Holiday> holidays = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false)
    )
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role)
    {
        if (role != null) {
            this.getRoles().add(role);
        }
    }

    public void copy(EmployeeUpdateCommand employeeCommand) {
        setId(employeeCommand.getId());
        setFirstName(employeeCommand.getFirstName());
        setLastName(employeeCommand.getLastName());
        setJobTitle(employeeCommand.getJobTitle());
        setStartDate(employeeCommand.getStartDate());
        setUsername(employeeCommand.getUsername());
        setPassword(employeeCommand.getPassword());
    }
}
