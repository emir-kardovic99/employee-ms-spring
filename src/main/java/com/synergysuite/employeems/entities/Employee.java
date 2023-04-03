package com.synergysuite.employeems.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}
