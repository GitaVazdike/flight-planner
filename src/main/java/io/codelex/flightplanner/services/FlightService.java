package io.codelex.flightplanner.services;

import io.codelex.flightplanner.objects.*;
import io.codelex.flightplanner.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void clearFlights() {
        flightRepository.clearFlights();
    }

    public Flight addFlight(AddFlightRequest addFlightRequest) {
        return flightRepository.addFlight(addFlightRequest);
    }

    public void deleteFlightById(long id) {
        flightRepository.deleteFlightById(id);
    }

    public Flight searchFlightById(long id) {
        return flightRepository.searchFlightById(id);
    }

    public List<Airport> getAirport(String search) {
        return flightRepository.getAirport(search);
    }

    public PageResult searchFlight(SearchFlightsRequest searchFlightsRequest) {
        return flightRepository.searchFlight(searchFlightsRequest);
    }

}

