package com.eoh.communication.Cic.repository.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
public class CicEntity {

    @Id
    @GeneratedValue
    private Long entityId;
    private String entityName;
    @Column(unique = true, nullable = false)
    private String emailAddress;

}
