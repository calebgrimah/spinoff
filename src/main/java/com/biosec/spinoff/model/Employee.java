package com.biosec.spinoff.model;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String countryOfResidence;
    private String gender;
    private String dob;
    private String nationality;
    private String stateOfOrigin;
    private String lga;
    private String addressLine;
    private String employeeId;
    private String employerId;
}
