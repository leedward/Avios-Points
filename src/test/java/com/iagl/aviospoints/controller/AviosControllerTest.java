package com.iagl.aviospoints.controller;

import com.iagl.aviospoints.model.Airport;
import com.iagl.aviospoints.model.Cabin;
import com.iagl.aviospoints.model.Route;
import com.iagl.aviospoints.repository.AirportRepository;
import com.iagl.aviospoints.repository.CabinRepository;
import com.iagl.aviospoints.repository.RouteRepository;
import com.iagl.aviospoints.service.AviosService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AviosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AviosService aviosService;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CabinRepository cabinRepository;

    @Autowired
    private RouteRepository routeRepository;

    @BeforeAll
    public void setup() {
        Cabin worldTraveller = new Cabin("M", 0);
        Cabin worldTravellerPlus = new Cabin("W", 20);
        Cabin clubWorld = new Cabin("J", 50);
        Cabin first = new Cabin("F", 100);
        cabinRepository.save(worldTraveller);
        cabinRepository.save(worldTravellerPlus);
        cabinRepository.save(clubWorld);
        cabinRepository.save(first);


        Airport LHR = new Airport("LHR");
        Airport JFK = new Airport("JFK");
        airportRepository.save(LHR);
        airportRepository.save(JFK);

        Route LHRtoJFK = new Route(LHR, JFK, 3200);
        routeRepository.save(LHRtoJFK);
    }

    @Test
    @Transactional
    public void testValidRequestWithCabinCode() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \"LHR\", \"arrivalAirportCode\": \"JFK\", \"cabinCode\": \"M\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.M").value(3200));
    }

    @Test
    @Transactional
    public void testValidRequestWithNoCabin() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \"LHR\", \"arrivalAirportCode\": \"JFK\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.M").value(3200))
                .andExpect(jsonPath("$.W").value(3840))
                .andExpect(jsonPath("$.J").value(4800))
                .andExpect(jsonPath("$.F").value(6400));
    }

    @Test
    @Transactional
    public void testInvalidDepartureAirportCode() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \"HLR\", \"arrivalAirportCode\": \"JFK\", \"cabinCode\": \"M\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Departure airport not found"));
    }

    @Test
    @Transactional
    public void testInvalidDepartureAirportCodeIncorrectCapitalisation() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \"lhr\", \"arrivalAirportCode\": \"JFK\", \"cabinCode\": \"M\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Departure airport not found"));
    }

    @Test
    @Transactional
    public void testInvalidDepartureAirportCodeWhitespace() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \" LHR \", \"arrivalAirportCode\": \"JFK\", \"cabinCode\": \"M\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Departure airport not found"));
    }

    @Test
    @Transactional
    public void testInvalidArrivalAirportCode() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \"LHR\", \"arrivalAirportCode\": \"FJK\", \"cabinCode\": \"M\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Arrival airport not found"));
    }

    @Test
    @Transactional
    public void testInvalidArrivalAirportCodeIncorrectCapitalisation() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \"LHR\", \"arrivalAirportCode\": \"jfk\", \"cabinCode\": \"M\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Arrival airport not found"));
    }

    @Test
    @Transactional
    public void testInvalidArrivalAirportCodeWhitespace() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \"LHR\", \"arrivalAirportCode\": \" JFK \", \"cabinCode\": \"M\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Arrival airport not found"));
    }

    @Test
    @Transactional
    public void testEmptyDepartureAirportCode() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \"\", \"arrivalAirportCode\": \"JFK\", \"cabinCode\": \"M\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("departureAirportCode: airport code cannot be empty"));
    }

    @Test
    @Transactional
    public void testNullDepartureAirportCode() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"arrivalAirportCode\": \"JFK\", \"cabinCode\": \"M\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("departureAirportCode: airport code cannot be empty"));
    }

    @Test
    @Transactional
    public void testEmptyArrivalAirportCode() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \"LHR\", \"arrivalAirportCode\": \"\", \"cabinCode\": \"M\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("arrivalAirportCode: airport code cannot be empty"));
    }

    @Test
    @Transactional
    public void testNullArrivalAirportCode() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \"LHR\", \"cabinCode\": \"M\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("arrivalAirportCode: airport code cannot be empty"));
    }

    @Test
    @Transactional
    public void testInvalidCabinCode() throws Exception {
        mockMvc.perform(post("/avios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \"LHR\", \"arrivalAirportCode\": \"JFK\", \"cabinCode\": \"Z\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Cabin Code not found"));
    }

}
