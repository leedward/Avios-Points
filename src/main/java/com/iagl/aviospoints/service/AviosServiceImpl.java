package com.iagl.aviospoints.service;

import com.iagl.aviospoints.exception.AirportNotFoundException;
import com.iagl.aviospoints.exception.CabinCodeNotFoundException;
import com.iagl.aviospoints.model.Airport;
import com.iagl.aviospoints.model.Cabin;
import com.iagl.aviospoints.model.Route;
import com.iagl.aviospoints.repository.AirportRepository;
import com.iagl.aviospoints.repository.CabinRepository;
import com.iagl.aviospoints.repository.RouteRepository;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class AviosServiceImpl implements AviosService {

    private static final Logger logger = LoggerFactory.getLogger(AviosServiceImpl.class);

    // Set the minimum Avios value
    private static final int MINIMUM_AVIOS = 500;

    // Declare the Route, Cabin, and Airport repositories
    private final RouteRepository routeRepository;
    private final CabinRepository cabinRepository;
    private final AirportRepository airportRepository;

    // Constructor injection for repositories
    public AviosServiceImpl(RouteRepository routeRepository, CabinRepository cabinRepository, AirportRepository airportRepository) {
        this.routeRepository = routeRepository;
        this.cabinRepository = cabinRepository;
        this.airportRepository = airportRepository;
    }

    // Method to calculate Avios based on departure airport, arrival airport, and cabin code
    public Map<String, Integer> calculateAvios(String departureAirportCode, String arrivalAirportCode, String cabinCode) {
        logger.debug("Calculating Avios: departureAirportCode={}, arrivalAirportCode={}, cabinCode={}", departureAirportCode, arrivalAirportCode, cabinCode);

        // Fetch the departure and arrival airports from database
        Airport departureAirport = airportRepository.findById(departureAirportCode).orElseThrow(() -> new AirportNotFoundException("Departure airport not found"));
        Airport arrivalAirport = airportRepository.findById(arrivalAirportCode).orElseThrow(() -> new AirportNotFoundException("Arrival airport not found"));

        // Fetch the route between departure and arrival airport
        Optional<Route> optionalRoute = routeRepository.findByDepartureAirportAndArrivalAirport(departureAirport, arrivalAirport);
        // Get Avios for the route or set to minimum if not found
        int routeAvios = optionalRoute.map(Route::getAvios).orElse(MINIMUM_AVIOS);
        logger.debug("Route Avios: {}", routeAvios);

        Map<String, Integer> aviosMap = new HashMap<>();

        // If cabinCode is provided, calculate Avios for the specific cabin
        if (cabinCode != null) {
            Cabin cabin = cabinRepository.findById(cabinCode).orElseThrow(() -> new CabinCodeNotFoundException("Cabin Code not found"));
            int cabinBonus = cabin.getBonus();
            int totalAvios = getTotalAvios(routeAvios, cabinBonus);
            logCabinTotalAvios(cabin, totalAvios);
            aviosMap.put(cabinCode, totalAvios);
        } else {
            // If no cabinCode is provided, calculate Avios for all cabins
            List<Cabin> cabins = cabinRepository.findAll();
            for (Cabin cabin : cabins) {
                int cabinBonus = cabin.getBonus();
                int totalAvios = getTotalAvios(routeAvios, cabinBonus);
                logCabinTotalAvios(cabin, totalAvios);
                aviosMap.put(cabin.getCode(), totalAvios);
            }
        }

        logger.debug("Calculated Avios: {}", aviosMap);
        return aviosMap;
    }

    // Method to log the total Avios for a cabin
    private void logCabinTotalAvios(Cabin cabin, int totalAvios) {
        logger.debug("Total Avios: cabinCode={}, totalAvios={}", cabin.getCode(), totalAvios);
    }

    // Method to calculate total Avios, includes route Avios and cabin bonus
    private int getTotalAvios(int routeAvios, int cabinBonus) {
        return routeAvios + routeAvios * cabinBonus / 100;
    }

}
