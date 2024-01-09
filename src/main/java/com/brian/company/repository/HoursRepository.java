package com.brian.company.repository;

import com.brian.company.models.Hours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HoursRepository extends JpaRepository<Hours, Long> {

    List<Hours> findHoursByEmployeeId(Long employeeId);
    @Query(value = "SELECT * FROM EMPLOYEE_WORKED_HOURS WHERE EMPLOYEE_ID = :employeeId AND WORKED_DATE BETWEEN :start AND :end", nativeQuery = true)
    List<Hours> findHoursByEmployeeIdAndWorkedDateBetween(@Param("employeeId") Long employeeId, @Param("start") LocalDate start, @Param("end") LocalDate end);

    Optional<Hours> findHoursByWorkedDate(LocalDate workedDate);
}
