package com.iagl.aviospoints.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

// An entity representing the Airport. Contains an ID, which is the airport code
@Entity
@Data
public class Airport {

    // The @Id annotation specifies the primary key of an entity.
    // The @Column annotation is used to specify the details of the column to which a field or property will be mapped.
    // The airport code serves as a unique identifier for the airport.
    @Id
    @Column(name = "code", length = 50, nullable = false)
    private String code;

    public Airport() {
    }

    public Airport(String code) {
        this.code = code;
    }
}
