package com.brian.company.service;

import com.brian.company.models.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    List<Job> getAll();
    Optional<Job> findById(Long id);
}
