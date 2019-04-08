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
public class ConvictionsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("Status")
    private Integer status;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("IncarcerationStartDate")
    private String incarcerationStartDate;
    @JsonProperty("IncarcerationEndDate")
    private String incarcerationEndDate;
    @JsonProperty("Offence")
    private String offence;
}