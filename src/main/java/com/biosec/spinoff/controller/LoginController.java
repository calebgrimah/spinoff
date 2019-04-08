package com.biosec.spinoff.controller;

import com.biosec.spinoff.database.LoginObject;
import com.biosec.spinoff.model.Employer;
import com.biosec.spinoff.repository.EmployerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    private EmployerRepository employerRepository;

    public LoginController(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }



    //login
    @PostMapping("spinoff/login")
    public ResponseEntity<?> login(@RequestBody LoginObject loginObject){
        Optional<Employer> byEmail = employerRepository.findByEmail(loginObject.getEmail());
        if (byEmail.isPresent()){
            //employer exists
            Employer employer = byEmail.get();
            if(employer.getPassword().equals(loginObject.getPassword())){
                return new ResponseEntity<>(employer, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
