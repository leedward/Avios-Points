package com.iagl.aviospoints.model;

import lombok.Data;

import javax.persistence.*;

// An entity representing a route, which connects two airports and has a base Avios value.
@Entity
@Data
public class Route {

    // Each route has a unique identifier.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The departure airport for this route.
    @ManyToOne
    private Airport departureAirport;

    // The arrival airport for this route.
    @ManyToOne
    private Airport arrivalAirport;

    // Base Avios value for this route.
    private int avios;

    public Route() {
    }

    public Route(Airport departureAirport, Airport arrivalAirport, int avios) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.avios = avios;
    }
}
