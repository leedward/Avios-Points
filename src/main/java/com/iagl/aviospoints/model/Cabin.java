package com.iagl.aviospoints.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

// An entity representing the Cabin. Contains an ID, which is the cabin code, and a bonus amount.
@Entity
@Data
public class Cabin {

    // The cabin code serves as a unique identifier for the cabin.
    @Id
    @Column(name = "code", length = 50, nullable = false)
    private String code;

    // Bonus points multiplier associated with this cabin type.
    private int bonus;

    public Cabin() {
    }

    public Cabin(String code, int bonus) {
        this.code = code;
        this.bonus = bonus;
    }
}
