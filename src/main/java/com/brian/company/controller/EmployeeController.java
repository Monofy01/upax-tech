package com.brian.company.controller;


import com.brian.company.dto.AddHoursDTO;
import com.brian.company.dto.CustomResponse;
import com.brian.company.dto.JobSearchDTO;
import com.brian.company.dto.TotalHoursDTO;
import com.brian.company.models.Employee;
import com.brian.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }

    @PostMapping("/save-hours")
    public ResponseEntity<Object> save(@RequestBody AddHoursDTO newHours) {
        return new ResponseEntity<>(employeeService.saveNewHours(newHours), HttpStatus.OK);
    }

    @PostMapping("/job-id")
    public ResponseEntity<Object> getEmployeesByJobId(@RequestBody JobSearchDTO jobSearchDTO) {
        return new ResponseEntity<>(employeeService.getEmployeeByIdJob(jobSearchDTO), HttpStatus.OK);
    }

    @PostMapping("/total-hours")
    public ResponseEntity<Object> totalHours(@RequestBody TotalHoursDTO hoursDTO) {
        return new ResponseEntity<>(employeeService.getTotalHoursBetween(hoursDTO), HttpStatus.OK);
    }

    @PostMapping("/total-payment")
    public ResponseEntity<Object> totalPayment(@RequestBody TotalHoursDTO hoursDTO) {
        return new ResponseEntity<>(employeeService.getTotalPaymentBetween(hoursDTO), HttpStatus.OK);
    }

}
