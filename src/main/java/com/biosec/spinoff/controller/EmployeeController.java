package com.biosec.spinoff.controller;

import com.biosec.spinoff.model.Employee;
import com.biosec.spinoff.model.Employer;
import com.biosec.spinoff.repository.EmployeeRepository;
import com.biosec.spinoff.repository.EmployerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    private EmployeeRepository employeeRepository;
    private EmployerRepository employerRepository;


    public EmployeeController(EmployeeRepository employeeRepository,
                              EmployerRepository employerRepository) {
        this.employeeRepository = employeeRepository;
        this.employerRepository = employerRepository;
    }

    @PostMapping("spinoff/register")
    public ResponseEntity<?> register(@RequestBody Employee employee){
//        Optional<Employer> em = employerRepository.findByEmployerId(employee.getEmployerId());
//        if(em.isPresent()){
//            Employer employer = em.get();
//
            employeeRepository.save(employee);
            return new ResponseEntity<>(employee,HttpStatus.CREATED);

//        }
    }

    @GetMapping("spinoff/single/{employeeId}")
    public ResponseEntity<?> getSingleEmployee(@PathVariable String employeeId){
        Optional<Employee> byEmployeeId = employeeRepository.findByEmployeeId(employeeId);
        if(byEmployeeId.isPresent()) return new ResponseEntity<>(byEmployeeId.get(),HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
