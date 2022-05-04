package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.api.AddFlightRequest;
import io.codelex.flightplanner.api.Flight;
import io.codelex.flightplanner.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api/flights")
public class AdminController {
    private FlightService flightService;

    public AdminController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Flight addFlight(@Valid @RequestBody AddFlightRequest addFlightRequest) {
        return flightService.addFlight(addFlightRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable("id") long id) {
        flightService.deleteFlightById(id);
    }

    @GetMapping("/{id}")
    public Flight searchFlightById(@PathVariable("id") long id) {
        return flightService.searchFlightById(id);
    }
}
