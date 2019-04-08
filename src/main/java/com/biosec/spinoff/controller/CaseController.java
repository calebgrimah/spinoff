package com.biosec.spinoff.controller;

import com.biosec.spinoff.model.CaseMS;
import com.biosec.spinoff.repository.CaseMsREpository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaseController {

    private CaseMsREpository caseMsREpository;

    @PostMapping("spinoff/case")
    public ResponseEntity<?> createCaseRecord(@RequestBody CaseMS caseMS){
        caseMsREpository.save(caseMS);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @GetMapping("spinoff/case")
//    public ResponseEntity<?> getSingleCase(){
//        caseMsREpository.save(caseMS);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }


}
