package com.biosec.spinoff.repository;

import com.biosec.spinoff.model.CaseMS;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CaseMsREpository extends CrudRepository<CaseMS, Long> {

    Optional<CaseMS> findByEnrollmentID(String enrollmentID);
}
