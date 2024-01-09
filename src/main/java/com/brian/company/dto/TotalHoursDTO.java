package com.brian.company.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TotalHoursDTO {

    private Long employee_id;
    private LocalDate start_date;
    private LocalDate end_date;
}
