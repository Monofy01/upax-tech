package com.brian.company.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEE_WORKED_HOURS")
@Data
@NoArgsConstructor
public class Hours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;
    @Column(name = "WORKED_HOURS")
    private Double workedHours;
    @Column(name = "WORKED_DATE")
    private LocalDate workedDate;

    public Hours(Long employeeId, Double workedHours, LocalDate workedDate) {
        this.employeeId = employeeId;
        this.workedHours = workedHours;
        this.workedDate = workedDate;
    }
}
