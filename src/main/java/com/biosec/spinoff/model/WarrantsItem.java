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
public class WarrantsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("Type")
    private Integer type;
    @JsonProperty("Priority")
    private Integer priority;
    @JsonProperty("RecordDate")
    private String recordDate;
    @JsonProperty("RequiredAction")
    private String requiredAction;
    @JsonProperty("Reason")
    private String reason;
}