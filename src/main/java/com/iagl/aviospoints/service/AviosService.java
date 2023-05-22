package com.iagl.aviospoints.service;

import java.util.Map;

public interface AviosService {
    Map<String, Integer> calculateAvios(String departureAirportCode, String arrivalAirportCode, String cabinCode);
}
