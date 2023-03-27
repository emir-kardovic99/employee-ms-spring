package com.synergysuite.employeems.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "start_date")
    private LocalDate startDate;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinTable(
            name = "employee_has_past_experience",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "past_experience_id"))
    private List<PastExperience> pastExperiences = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinTable(
            name = "employee_has_holiday",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "holiday_id"))
    private List<Holiday> holidays = new ArrayList<>();
}
