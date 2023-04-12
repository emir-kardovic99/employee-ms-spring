package com.synergysuite.employeems.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "holiday")
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String reason;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_from", nullable = false)
    private LocalDate dateFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_to", nullable = false)
    private LocalDate dateTo;

    @ToString.Exclude
    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "is_approved")
    private Boolean isApproved;

}
