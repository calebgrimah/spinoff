package com.biosec.spinoff.model;

/**
 * Created by JacksonGenerator on 04/03/19.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CaseRecordsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("PoliceStation")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "POLICE_STATION_ID")
    private PoliceStation policeStation;

    @JsonProperty("PrimaryCharge")
    private String primaryCharge;

    @JsonProperty("RecordDate")
    private String recordDate;

    @JsonProperty("BailStatus")
    private Integer bailStatus;
}