package com.biosec.spinoff.model;

/**
 * Created by JacksonGenerator on 04/03/19.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class PoliceStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long psid;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("ID")
    private Integer iD;

    @JsonProperty("Name")
    private String name;
}