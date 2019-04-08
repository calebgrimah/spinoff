package com.biosec.spinoff.repository;


import com.biosec.spinoff.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    Optional<Employee> findByEmployeeId(String employeeId);
}
