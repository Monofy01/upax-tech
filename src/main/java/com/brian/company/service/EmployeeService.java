package com.brian.company.service;

import com.brian.company.dto.AddHoursDTO;
import com.brian.company.dto.JobSearchDTO;
import com.brian.company.dto.TotalHoursDTO;
import com.brian.company.models.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAll();

    Object save(Employee employee);
    Optional<Employee> findEmployeeByNameAndLastName(String name, String lastName);

    Map<String, Object> saveNewHours(AddHoursDTO newHours);

    Map<String, Object> getEmployeeByIdJob(JobSearchDTO jobMetada);
    Map<String, Object> getTotalHoursBetween(TotalHoursDTO hoursDTO);

    Map<String, Object> getTotalPaymentBetween(TotalHoursDTO hoursDTO);
}
