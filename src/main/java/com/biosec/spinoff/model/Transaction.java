package com.biosec.spinoff.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    private String employerId;
    private String employeeId;
    private String companyName;
    private String transactionDate;
    private Integer isTransactionSuccessful;
    private Integer paymentStatus;
}
