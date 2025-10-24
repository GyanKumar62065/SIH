package com.sundram.aictetaskmanagement.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Entity(name="Scholarship")
@Data
@ToString
public class ScholarShip {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int scholarShipId;


    private String scholarShipName;

    private String amount;

    private String information;
}
