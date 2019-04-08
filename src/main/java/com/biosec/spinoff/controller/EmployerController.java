package com.biosec.spinoff.controller;

import com.biosec.spinoff.model.Employee;
import com.biosec.spinoff.model.Employer;
import com.biosec.spinoff.repository.EmployerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployerController {

    private EmployerRepository employerRepository;


    public EmployerController(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @PostMapping("spinoff/employer")
    public ResponseEntity<?> createEmployer (@RequestBody Employer employer){
     employerRepository.save(employer);
     return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
