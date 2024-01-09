package com.brian.company.service.impl;

import com.brian.company.dto.AddHoursDTO;
import com.brian.company.dto.CustomResponse;
import com.brian.company.dto.JobSearchDTO;
import com.brian.company.dto.TotalHoursDTO;
import com.brian.company.models.Employee;
import com.brian.company.models.Hours;
import com.brian.company.models.Job;
import com.brian.company.repository.EmployeeRepository;
import com.brian.company.service.EmployeeService;
import com.brian.company.service.GenderService;
import com.brian.company.service.HoursService;
import com.brian.company.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;
    private GenderService genderService;
    private JobService jobService;
    private HoursService hoursService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, GenderService genderService, JobService jobService, HoursService hoursService) {
        this.employeeRepository = employeeRepository;
        this.genderService = genderService;
        this.jobService = jobService;
        this.hoursService = hoursService;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Object save(Employee employee) {
        try {
            List<String> errors = new ArrayList<>();
            // Check existence
            if (jobService.findById(employee.getJobId()).isEmpty())
                errors.add(("Job ID doesn't exist"));
            if (genderService.findById(employee.getGenderId()).isEmpty())
                errors.add(("Gender ID doesn't exist"));
            if (    findEmployeeByNameAndLastName(employee.getName(), employee.getLastName()).isPresent())
                errors.add(("Name and LastName already exists"));




            if (errors.isEmpty())
                return employeeRepository.save(employee);
            return new CustomResponse(errors, false);
        } catch (DataIntegrityViolationException dive) {
            log.info(dive.toString());
            return new CustomResponse(dive.getMessage(), false);
        } catch (Exception e) {
            log.info(e.toString());
            return new CustomResponse(e.getMessage(), false);
        }
    }

    @Override
    public Optional<Employee> findEmployeeByNameAndLastName(String name, String lastName) {
        return employeeRepository.findEmployeeByNameAndLastName(name, lastName);
    }

    @Override
    public Map<String, Object> saveNewHours(AddHoursDTO newHours) {
        // TODO: 2. Realiza un web service que permita agregar horas trabajadas de un empleado.
        //  -- Se debe validar que el empleado exista,
        //  -- que el total de horas trabajadas no sea mayor a 20 horas
        //  -- que la fecha de trabajo sea menor o igual a la actual
        //  -- y no se duplique por empleado (un empleado sólo puede tener un registro de horas trabajadas por día).


        try {
            Optional<Employee> employeeFound = employeeRepository.findById(newHours.getEmployee_id());

            if (employeeFound.isEmpty())
                throw new RuntimeException("Employee not found");

            if (newHours.getWorked_hours() > 20)
                throw new RuntimeException("Worked hours not more than 20 hours");

            if (newHours.getWorked_date().isAfter(LocalDate.now()) || newHours.getWorked_date().equals(LocalDate.now()))
                throw new RuntimeException("Worked date is not minor that now or equals");

            Optional<Hours> hoursSpecificDate = hoursService.getHoursByWorkedDate(newHours.getWorked_date());
            if (hoursSpecificDate.isPresent())
                throw new RuntimeException("Hours for today has been inserted");


            List<Hours> hoursFound = hoursService.getHoursByEmployee(newHours.getEmployee_id());
            if (!hoursFound.isEmpty()) {
                List<Hours> listHoursToday = hoursFound.stream().filter(hours -> hours.getWorkedDate().equals(LocalDate.now())).toList();
                if (!listHoursToday.isEmpty())
                    throw new RuntimeException("The employee have a list of hours for today");
            }

            Hours hoursAdded = hoursService.save(new Hours(
                    newHours.getEmployee_id(),
                    newHours.getWorked_hours(),
                    newHours.getWorked_date()
            ));

            Map<String, Object> response = new HashMap<>();
            response.put("id", hoursAdded.getId());
            response.put("success", true);
            return response;
        } catch (Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex.getMessage());
            response.put("success", false);
            return response;
        }
    }


    @Override
    public Map<String, Object> getEmployeeByIdJob(JobSearchDTO jobMetada) {
        // TODO: 3. Realiza un web service que permita
        //  consultar los empleados por puesto
        //  -- Se debe validar que el puesto exista.
        Optional<Job> jobFound = jobService.findById(jobMetada.getJob_id());
        if (jobFound.isPresent()) {
            List<Employee> employeeList = employeeRepository.findEmployeeByJobId(jobMetada.getJob_id());
            Map<String, Object> response = new HashMap<>();
            response.put("employeess", employeeList);
            response.put("success", true);
            return response;
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "JobId doesnt exist");
            response.put("success", false);
            return response;
        }
    }

    @Override
    public Map<String, Object> getTotalHoursBetween(TotalHoursDTO hoursDTO) {
        // TODO: 4. Realiza un web service que permita consultar
        //  el total de horas trabajadas de un empleado por rango de fechas.
        //  -- Se debe validar que el empleado exista y que la fecha de inicio sea menor a la fecha de fin
        double totalHours = 0.0;
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Employee> employeeFound = employeeRepository.findById(hoursDTO.getEmployee_id());
            if (employeeFound.isEmpty())
                throw new RuntimeException("Employee not found");
            List<Hours> hoursTotal = getHours(hoursDTO);
            totalHours = hoursTotal.stream().mapToDouble(Hours::getWorkedHours).sum();
            response.put("total_worked_hours", totalHours);
            response.put("success", true);
        } catch (Exception ex) {
            response.put("error", ex.getMessage());
            response.put("success", false);
        }
        return response;
    }

    private List<Hours> getHours(TotalHoursDTO hoursDTO) {
        if (hoursDTO.getEnd_date().isBefore(hoursDTO.getStart_date()))
            throw new RuntimeException("Range between start and end date is not valid");

        List<Hours> hoursTotal = hoursService.getTotalHoursBetween(
                hoursDTO.getEmployee_id(),
                hoursDTO.getStart_date(),
                hoursDTO.getEnd_date()
        );
        return hoursTotal;
    }

    @Override
    public Map<String, Object> getTotalPaymentBetween(TotalHoursDTO hoursDTO) {
        // TODO 5. Realiza un web service que permita consultar
        //  cuanto se le pagó a un empleado en un rango de fechas.
        //  Se debe validar que el empleado exista y que la fecha
        //  de inicio sea menor a la fecha de fin.
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Employee> employeeFound = employeeRepository.findById(hoursDTO.getEmployee_id());
            if (employeeFound.isEmpty())
                throw new RuntimeException("Employee not found");
            Optional<Job> jobFound = jobService.findById(employeeFound.get().getJobId());
            if (jobFound.isEmpty())
                throw new RuntimeException("The JobId from Employee not found");

            List<Hours> hoursTotal = getHours(hoursDTO);
            Double payment = hoursTotal.size() * jobFound.get().getSalary();

            response.put("payment", payment);
            response.put("success", true);
        } catch (Exception ex) {
            response.put("error", ex.getMessage());
            response.put("success", false);
        }
        return response;
    }

}
