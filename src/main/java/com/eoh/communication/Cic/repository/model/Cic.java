package com.eoh.communication.Cic.repository.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Data
@Entity
public class Cic {

    @Id
    @GeneratedValue
    private Long cicId;
    private String cicType;
    private String subject;
    private String body;
    private String sourceSystem;
    private LocalDateTime cicTimeStamp;
    @ManyToOne
    CicEntity entity;
}
