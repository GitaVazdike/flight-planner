package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.api.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private long idCounter = 0L;
    private final List<Flight> listOfFlights = new ArrayList<>();

    public void clearFlights() {
        listOfFlights.clear();
    }

    public synchronized Flight addFlight(AddFlightRequest addFlightRequest) {
        Flight flight = new Flight(idCounter, addFlightRequest);

        if (listOfFlights.contains(flight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        if (flight.getFrom().isTheSameAirport(addFlightRequest) || isInvalidDate(flight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        idCounter++;
        listOfFlights.add(flight);
        return flight;
    }

    public synchronized void deleteFlightById(long id) {
        listOfFlights.removeIf(flight -> flight.getId() == id);
    }

    public Flight searchFlightById(long id) {
        return listOfFlights.stream()
                .filter(flight -> flight.getId() == id)
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Airport> getAirport(String search) {
        return listOfFlights.stream()
                .map(Flight::getFrom)
                .filter(airport -> airport.matchesSearchedPhrase(search))
                .toList();
    }

    public PageResult searchFlight(SearchFlightsRequest searchFlightsRequest) {

        if (searchFlightsRequest.getFrom().equals(searchFlightsRequest.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return new PageResult(listOfFlights
                .stream()
                .filter(flight -> flight.matchesSearchRequest(searchFlightsRequest))
                .toList());
    }

    private boolean isInvalidDate(Flight flight) {
        return flight.getDepartureTime().equals(flight.getArrivalTime())
                || flight.getDepartureTime().isAfter(flight.getArrivalTime());
    }

}



