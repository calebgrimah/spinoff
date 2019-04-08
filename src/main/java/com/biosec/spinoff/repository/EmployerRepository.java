package com.biosec.spinoff.repository;

import com.biosec.spinoff.model.Employer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployerRepository extends CrudRepository<Employer, Long> {
    Optional<Employer> findByEmployerId(String employerId);
    Optional<Employer> findByEmail(String email);

}
