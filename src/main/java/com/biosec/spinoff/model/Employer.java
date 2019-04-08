package com.biosec.spinoff.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;
    private String gender;
    private String dob;
    private String nationality;
    private String addressLine;
    private String countryOfResidence;
    private String nin;
    private String driversLicence;
    private String passportNumber;
    private String mobileNumber;
    private String email;
    private String password;
    private String companyName;
    private String webUrl;
    private String managementOfficialName;
    private String employerId;
}
