package com.brian.company.service;

import com.brian.company.models.Gender;
import com.brian.company.models.Job;

import java.util.List;
import java.util.Optional;

public interface GenderService {

    List<Gender> getAll();

    Optional<Gender> findById(Long id);
}
