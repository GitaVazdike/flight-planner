package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.services.FlightService;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    private FlightService flightService;

    public TestController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/testing-api/clear")
    public void clearFlights() {
        flightService.clearFlights();
    }

}