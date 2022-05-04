package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.api.Airport;
import io.codelex.flightplanner.api.Flight;
import io.codelex.flightplanner.api.PageResult;
import io.codelex.flightplanner.api.SearchFlightsRequest;
import io.codelex.flightplanner.services.FlightService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private FlightService flightService;

    public CustomerController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/airports")
    public List<Airport> getAirport(@PathParam("search") String search) {
        return flightService.getAirport(search);
    }

    @PostMapping("/flights/search")
    public PageResult searchFlight(@Valid @RequestBody SearchFlightsRequest searchFlightsRequest) {
        return flightService.searchFlight(searchFlightsRequest);
    }

    @GetMapping("/flights/{id}")
    public Flight searchFlightById(@PathVariable("id") long id) {
        return flightService.searchFlightById(id);
    }
}
