package com.iagl.aviospoints.service;

import com.iagl.aviospoints.model.Airport;
import com.iagl.aviospoints.model.Cabin;
import com.iagl.aviospoints.model.Route;
import com.iagl.aviospoints.repository.AirportRepository;
import com.iagl.aviospoints.repository.CabinRepository;
import com.iagl.aviospoints.repository.RouteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AviosServiceTest {

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
        Airport LGW = new Airport("LGW");
        Airport LAX = new Airport("LAX");
        Airport SFO = new Airport("SFO");
        Airport JFK = new Airport("JFK");
        Airport YYZ = new Airport("YYZ");
        Airport STN = new Airport("STN");
        airportRepository.save(LHR);
        airportRepository.save(LGW);
        airportRepository.save(LAX);
        airportRepository.save(SFO);
        airportRepository.save(JFK);
        airportRepository.save(YYZ);
        airportRepository.save(STN);

        Route LHRtoLAX = new Route(LHR, LAX, 4500);
        Route LHRtoSFO = new Route(LHR, SFO, 4400);
        Route LHRtoJFK = new Route(LHR, JFK, 3200);
        Route LGWtoYYZ = new Route(LGW, YYZ, 3250);

        routeRepository.save(LHRtoLAX);
        routeRepository.save(LHRtoSFO);
        routeRepository.save(LHRtoJFK);
        routeRepository.save(LGWtoYYZ);
    }

    //Tests for defined route with cabin code
    @Test
    @Transactional
    public void testCalculateAviosLhrToLaxWithCabinM() {
        // Test LHR to LAX with cabin code "M"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "LAX", "M");
        assertEquals(1, aviosMap.size());
        assertEquals(4500, aviosMap.get("M").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToSfoWithCabinM() {
        // Test LHR to SFO with cabin code "M"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "SFO", "M");
        assertEquals(1, aviosMap.size());
        assertEquals(4400, aviosMap.get("M").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToJfkWithCabinM() {
        // Test LHR to JFK with cabin code "M"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "JFK", "M");
        assertEquals(1, aviosMap.size());
        assertEquals(3200, aviosMap.get("M").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLgwToYyzWithCabinM() {
        // Test LGW to YYZ with cabin code "M"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LGW", "YYZ", "M");
        assertEquals(1, aviosMap.size());
        assertEquals(3250, aviosMap.get("M").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToLaxWithCabinW() {
        // Test LHR to LAX with cabin code "W"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "LAX", "W");
        assertEquals(1, aviosMap.size());
        assertEquals(5400, aviosMap.get("W").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToSfoWithCabinW() {
        // Test LHR to SFO with cabin code "W"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "SFO", "W");
        assertEquals(1, aviosMap.size());
        assertEquals(5280, aviosMap.get("W").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToJfkWithCabinW() {
        // Test LHR to JFK with cabin code "W"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "JFK", "W");
        assertEquals(1, aviosMap.size());
        assertEquals(3840, aviosMap.get("W").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLgwToYyzWithCabinW() {
        // Test LGW to YYZ with cabin code "W"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LGW", "YYZ", "W");
        assertEquals(1, aviosMap.size());
        assertEquals(3900, aviosMap.get("W").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToLaxWithCabinJ() {
        // Test LHR to LAX with cabin code "J"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "LAX", "J");
        assertEquals(1, aviosMap.size());
        assertEquals(6750, aviosMap.get("J").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToSfoWithCabinJ() {
        // Test LHR to SFO with cabin code "J"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "SFO", "J");
        assertEquals(1, aviosMap.size());
        assertEquals(6600, aviosMap.get("J").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToJfkWithCabinJ() {
        // Test LHR to JFK with cabin code "J"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "JFK", "J");
        assertEquals(1, aviosMap.size());
        assertEquals(4800, aviosMap.get("J").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLgwToYyzWithCabinJ() {
        // Test LGW to YYZ with cabin code "J"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LGW", "YYZ", "J");
        assertEquals(1, aviosMap.size());
        assertEquals(4875, aviosMap.get("J").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToLaxWithCabinF() {
        // Test LHR to LAX with cabin code "F"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "LAX", "F");
        assertEquals(1, aviosMap.size());
        assertEquals(9000, aviosMap.get("F").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToSfoWithCabinF() {
        // Test LHR to SFO with cabin code "F"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "SFO", "F");
        assertEquals(1, aviosMap.size());
        assertEquals(8800, aviosMap.get("F").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToJfkWithCabinF() {
        // Test LHR to JFK with cabin code "F"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "JFK", "F");
        assertEquals(1, aviosMap.size());
        assertEquals(6400, aviosMap.get("F").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLgwToYyzWithCabinF() {
        // Test LGW to YYZ with cabin code "F"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LGW", "YYZ", "F");
        assertEquals(1, aviosMap.size());
        assertEquals(6500, aviosMap.get("F").intValue());
    }

    //Tests for route without cabin code
    @Test
    @Transactional
    public void testCalculateAviosLhrToLaxWithoutCabin() {
        // Test LHR to LAX without cabin code
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "LAX", null);
        assertEquals(4, aviosMap.size());
        assertEquals(4500, aviosMap.get("M").intValue());
        assertEquals(5400, aviosMap.get("W").intValue());
        assertEquals(6750, aviosMap.get("J").intValue());
        assertEquals(9000, aviosMap.get("F").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToSfoWithoutCabin() {
        // Test LHR to SFO without cabin code
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "SFO", null);
        assertEquals(4, aviosMap.size());
        assertEquals(4400, aviosMap.get("M").intValue());
        assertEquals(5280, aviosMap.get("W").intValue());
        assertEquals(6600, aviosMap.get("J").intValue());
        assertEquals(8800, aviosMap.get("F").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLhrToJfkWithoutCabin() {
        // Test LHR to JFK without cabin code
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LHR", "JFK", null);
        assertEquals(4, aviosMap.size());
        assertEquals(3200, aviosMap.get("M").intValue());
        assertEquals(3840, aviosMap.get("W").intValue());
        assertEquals(4800, aviosMap.get("J").intValue());
        assertEquals(6400, aviosMap.get("F").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosLgwToYyzWithoutCabin() {
        // Test LGW to YYZ without cabin code
        Map<String, Integer> aviosMap = aviosService.calculateAvios("LGW", "YYZ", null);
        assertEquals(4, aviosMap.size());
        assertEquals(3250, aviosMap.get("M").intValue());
        assertEquals(3900, aviosMap.get("W").intValue());
        assertEquals(4875, aviosMap.get("J").intValue());
        assertEquals(6500, aviosMap.get("F").intValue());
    }

    //Tests for undefined route with Cabin Code
    @Test
    @Transactional
    public void testCalculateAviosStnToYyzWithCabinM() {
        // Test STN to YYZ with cabin code "M"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("STN", "YYZ", "M");
        assertEquals(1, aviosMap.size());
        assertEquals(500, aviosMap.get("M").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosStnToYyzWithCabinW() {
        // Test STN to YYZ with cabin code "W"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("STN", "YYZ", "W");
        assertEquals(1, aviosMap.size());
        assertEquals(600, aviosMap.get("W").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosStnToYyzWithCabinJ() {
        // Test STN to YYZ with cabin code "J"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("STN", "YYZ", "J");
        assertEquals(1, aviosMap.size());
        assertEquals(750, aviosMap.get("J").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosStnToYyzWithCabinF() {
        // Test STN to YYZ with cabin code "F"
        Map<String, Integer> aviosMap = aviosService.calculateAvios("STN", "YYZ", "F");
        assertEquals(1, aviosMap.size());
        assertEquals(1000, aviosMap.get("F").intValue());
    }

    @Test
    @Transactional
    public void testCalculateAviosStnToYyzWithoutCabin() {
        // Test LGW to YYZ without cabin code
        Map<String, Integer> aviosMap = aviosService.calculateAvios("STN", "YYZ", null);
        assertEquals(4, aviosMap.size());
        assertEquals(500, aviosMap.get("M").intValue());
        assertEquals(600, aviosMap.get("W").intValue());
        assertEquals(750, aviosMap.get("J").intValue());
        assertEquals(1000, aviosMap.get("F").intValue());
    }

}
