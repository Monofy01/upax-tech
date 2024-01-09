package com.brian.company.service;

import com.brian.company.models.Employee;
import com.brian.company.models.Hours;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HoursService {

    List<Hours> getAll();

    Hours save(Hours hours);

    List<Hours> getHoursByEmployee(Long employeeId);
    List<Hours> getTotalHoursBetween(Long employeeId, LocalDate start, LocalDate end);

    Optional<Hours> getHoursByWorkedDate(LocalDate workedDate);
}
