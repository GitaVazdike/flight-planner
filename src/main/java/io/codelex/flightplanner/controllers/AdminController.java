package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.objects.AddFlightRequest;
import io.codelex.flightplanner.objects.Flight;
import io.codelex.flightplanner.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api")
public class AdminController {
    private FlightService flightService;

    public AdminController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PutMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Flight addFlight(@Valid @RequestBody AddFlightRequest addFlightRequest) {
        return flightService.addFlight(addFlightRequest);
    }

    @DeleteMapping("/flights/{id}")
    public synchronized void deleteFlight(@PathVariable("id") long id) {
        flightService.deleteFlightById(id);
    }

    @GetMapping("/flights/{id}")
    public Flight searchFlightById(@PathVariable("id") long id) {
        return flightService.searchFlightById(id);
    }
}
