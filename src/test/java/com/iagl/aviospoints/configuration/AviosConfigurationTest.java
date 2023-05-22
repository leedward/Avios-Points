package com.iagl.aviospoints.configuration;

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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AviosConfigurationTest {

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
        Cabin traveller = new Cabin("T", 0);
        cabinRepository.save(traveller);

        Airport LGW = new Airport("LGW");
        Airport GLA = new Airport("GLA");
        airportRepository.save(LGW);
        airportRepository.save(GLA);


        Route LGWtoGLA = new Route(LGW, GLA, 3200);
        routeRepository.save(LGWtoGLA);
    }

    @Test
    @Transactional
    public void corsConfigurationTestPost() throws Exception {
        mockMvc.perform(post("/avios")
                        .header(HttpHeaders.ORIGIN, "http://test.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departureAirportCode\": \"LGW\", \"arrivalAirportCode\": \"GLA\", \"cabinCode\": \"T\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void corsConfigurationTestGet() throws Exception {
        mockMvc.perform(get("/avios")
                        .header(HttpHeaders.ORIGIN, "http://test.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @Transactional
    public void corsConfigurationTestDelete() throws Exception {
        mockMvc.perform(delete("/avios")
                        .header(HttpHeaders.ORIGIN, "http://test.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @Transactional
    public void corsConfigurationTestPatch() throws Exception {
        mockMvc.perform(patch("/avios")
                        .header(HttpHeaders.ORIGIN, "http://test.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }
}
