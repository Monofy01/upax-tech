package com.brian.company.service.impl;

import com.brian.company.models.Gender;
import com.brian.company.models.Job;
import com.brian.company.repository.GenderRepository;
import com.brian.company.repository.JobRepository;
import com.brian.company.service.GenderService;
import com.brian.company.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {


    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public Optional<Job> findById(Long id) {
        return jobRepository.findById(id);
    }
}
