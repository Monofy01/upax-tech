package com.brian.company.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddHoursDTO {

    private Long employee_id;
    private Double worked_hours;
    private LocalDate worked_date;
}
