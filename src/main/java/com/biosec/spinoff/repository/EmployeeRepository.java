package com.biosec.spinoff.repository;


import com.biosec.spinoff.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}
