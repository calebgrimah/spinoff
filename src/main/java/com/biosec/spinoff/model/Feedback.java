package com.biosec.spinoff.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Feedback {
    private String message;
    private String email;
    private Employee employee;
}
