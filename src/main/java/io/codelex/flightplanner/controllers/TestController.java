package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.api.Airport;
import io.codelex.flightplanner.database.AirportDatabaseService;
import io.codelex.flightplanner.services.FlightService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testing-api")
public class TestController {

    private FlightService flightService;
    private AirportDatabaseService airportDatabaseService;

    public TestController(FlightService flightService, AirportDatabaseService airportDatabaseService) {
        this.flightService = flightService;
        this.airportDatabaseService = airportDatabaseService;
    }

    @PostMapping("/clear")
    public void clearFlights() {
        flightService.clearFlights();
    }

    @GetMapping("/add-test-flight")
    public void addTestFlight() {
        airportDatabaseService.addAirport(new Airport("Latvia", "Riga", "RIX"));
    }
}