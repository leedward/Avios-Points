package com.iagl.aviospoints.repository;

import com.iagl.aviospoints.model.Airport;
import com.iagl.aviospoints.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByDepartureAirportAndArrivalAirport(Airport departure, Airport arrival);
}
