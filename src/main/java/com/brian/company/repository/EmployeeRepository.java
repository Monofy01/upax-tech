package com.brian.company.repository;

import com.brian.company.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByNameAndLastName(String name, String lastName);
    List<Employee> findEmployeeByJobId(Long jobId);
}
