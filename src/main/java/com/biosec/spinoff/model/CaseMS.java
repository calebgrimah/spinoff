package com.biosec.spinoff.model;

/**
 * Created by JacksonGenerator on 04/03/19.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class CaseMS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("CaseRecords")
//    @Column(name = "CaseREcords_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<CaseRecordsItem> caseRecords = new ArrayList<>();

    @JsonProperty("NumberOfConvictions")
    private Integer numberOfConvictions;
    @JsonProperty("EnrollmentID")
    private String enrollmentID;
   // @JsonProperty("Convictions")
    @OneToMany(cascade = CascadeType.ALL)
    private List<ConvictionsItem> convictions = new ArrayList<>();
    @JsonProperty("Warrants")
    @OneToMany(cascade = CascadeType.ALL)
    private List<WarrantsItem> warrants = new ArrayList<>();
}