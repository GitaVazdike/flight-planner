package io.codelex.flightplanner.services;

import io.codelex.flightplanner.api.*;
import io.codelex.flightplanner.repository.FlightRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "flight-planner", name = "store-type", havingValue = "in-memory")
public class FlightServiceInMemoryImpl extends AbstractFlightService implements FlightService  {

    private FlightRepository flightRepository;

    public FlightServiceInMemoryImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void clearFlights() {
        flightRepository.clearFlights();
    }

    public Flight addFlight(AddFlightRequest addFlightRequest) {
        Flight flight = new Flight(addFlightRequest);

        if (flight.getFrom().isTheSameAirport(addFlightRequest) || isInvalidDate(flight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return flightRepository.addFlight(flight);
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

