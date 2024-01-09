package com.brian.company.service.impl;

import com.brian.company.models.Hours;
import com.brian.company.repository.HoursRepository;
import com.brian.company.service.HoursService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HoursServiceImpl implements HoursService {


    private HoursRepository hoursRepository;

    @Autowired
    public HoursServiceImpl(HoursRepository hoursRepository) {
        this.hoursRepository = hoursRepository;
    }

    @Override
    public List<Hours> getAll() {
        return null;
    }

    @Override
    public Hours save(Hours hours) {
        return hoursRepository.save(hours);
    }

    @Override
    public List<Hours> getHoursByEmployee(Long employeeId) {
        return hoursRepository.findHoursByEmployeeId(employeeId);
    }

    @Override
    public List<Hours> getTotalHoursBetween(Long employeeId, LocalDate start, LocalDate end) {
        return hoursRepository.findHoursByEmployeeIdAndWorkedDateBetween(employeeId,start, end);
    }

    @Override
    public Optional<Hours> getHoursByWorkedDate(LocalDate workedDate) {
        return hoursRepository.findHoursByWorkedDate(workedDate);
    }
}
