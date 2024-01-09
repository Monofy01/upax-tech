package com.brian.company.controller;


import com.brian.company.models.Employee;
import com.brian.company.models.Gender;
import com.brian.company.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/gender")
@Controller
public class GenderController {

    private GenderService genderService;

    @Autowired
    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Gender>> getAll() {
        return new ResponseEntity<>(genderService.getAll(), HttpStatus.OK);
    }
}
