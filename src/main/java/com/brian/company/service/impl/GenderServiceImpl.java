package com.brian.company.service.impl;

import com.brian.company.models.Gender;
import com.brian.company.repository.GenderRepository;
import com.brian.company.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderServiceImpl implements GenderService {


    private GenderRepository genderRepository;

    @Autowired
    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public List<Gender> getAll() {
        return genderRepository.findAll();
    }

    @Override
    public Optional<Gender> findById(Long id) {
        return genderRepository.findById(id);
    }
}
