package com.synergysuite.employeems.entities;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "past_experience")
public class PastExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_from", nullable = false)
    private LocalDate dateFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_to", nullable = false)
    private LocalDate dateTo;

    @Column(name = "employee_id", nullable = false)
    @ToString.Exclude
    private Integer employeeId;

}
