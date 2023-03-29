package com.synergysuite.employeems.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString
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
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = PastExperience.class)
    @ToString.Exclude
    @JsonManagedReference
    private List<PastExperience> pastExperiences = new ArrayList<>();

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = Holiday.class)
    @ToString.Exclude
    @JsonManagedReference
    private List<Holiday> holidays = new ArrayList<>();
}
