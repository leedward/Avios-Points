package com.iagl.aviospoints.controller;

import com.iagl.aviospoints.model.AviosRequest;
import com.iagl.aviospoints.service.AviosServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

// Controller responsible for handling Avios points calculation requests
@Api(value = "Avios Controller")
@RestController
public class AviosController {

    // Service class instance used to perform Avios points calculation
    private final AviosServiceImpl aviosService;

    // Constructor to inject the AviosServiceImpl instance
    public AviosController(AviosServiceImpl aviosService) {
        this.aviosService = aviosService;
    }

    // Endpoint to calculate Avios points
    // It accepts a POST request with the AviosRequest object as the request body
    @ApiOperation(value = "Calculate Avios points")
    @PostMapping("/avios")
    public Map<String, Integer> calculateAvios(@RequestBody @Valid AviosRequest aviosRequest) {
        // Calls the Avios service to calculate the Avios points
        // based on the request parameters
        return aviosService.calculateAvios(aviosRequest.getDepartureAirportCode(),
                aviosRequest.getArrivalAirportCode(),
                aviosRequest.getCabinCode());
    }
}
