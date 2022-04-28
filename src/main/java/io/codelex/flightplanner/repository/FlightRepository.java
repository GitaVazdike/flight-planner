package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.objects.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private long idCounter = 0L;
    private final List<Flight> listOfFlights = new ArrayList<>();

    public void clearFlights() {
        listOfFlights.clear();
    }

    public Flight addFlight(AddFlightRequest addFlightRequest) {
        Flight flight = new Flight(idCounter, addFlightRequest);

        if (listOfFlights.contains(flight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        if (isTheSameAirport(addFlightRequest) || isInvalidDate(flight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        idCounter++;
        listOfFlights.add(flight);
        return flight;
    }

    public void deleteFlightById(long id) {
        listOfFlights.removeIf(flight -> flight.getId() == id);
    }

    public Flight searchFlightById(long id) {
        Flight flightFound = null;
        for (Flight flight : listOfFlights) {
            if (flight.getId() == id) {
                flightFound = flight;
            }
        }
        if (flightFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return flightFound;
    }

    public List<Airport> getAirport(String search) {
        List<Airport> airportList = new ArrayList<>();
        String formattedSearchString = search.trim().toUpperCase();

        for (Flight flight : listOfFlights) {
            if (flight.getFrom().getCountry().toUpperCase().contains(formattedSearchString)
                    || flight.getFrom().getCity().toUpperCase().contains(formattedSearchString)
                    || flight.getFrom().getAirport().toUpperCase().contains(formattedSearchString)) {
                airportList.add(flight.getFrom());
            }
        }
        return airportList;
    }

    public PageResult searchFlight(SearchFlightsRequest searchFlightsRequest) {
        List<Flight> flightsFound = new ArrayList<>();

        if (searchFlightsRequest.getFrom().equalsIgnoreCase(searchFlightsRequest.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        for (Flight flight : listOfFlights) {
            String flightDepartureDateToString = flight.getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (flight.getFrom().getAirport().equals(searchFlightsRequest.getFrom())
                    && flight.getTo().getAirport().equals(searchFlightsRequest.getTo())
                    && flightDepartureDateToString.equals(searchFlightsRequest.getDepartureDate())) {
                flightsFound.add(flight);
            }
        }
        return new PageResult(0, flightsFound.size(), flightsFound);
    }

    private boolean isTheSameAirport(AddFlightRequest addFlightRequest) {
        return addFlightRequest.getFrom().getAirport().trim().equalsIgnoreCase(addFlightRequest.getTo().getAirport().trim())
                && addFlightRequest.getFrom().getCity().trim().equalsIgnoreCase(addFlightRequest.getTo().getCity().trim())
                && addFlightRequest.getFrom().getCountry().trim().equalsIgnoreCase(addFlightRequest.getTo().getCountry().trim());
    }

    private boolean isInvalidDate(Flight flight) {
        return flight.getDepartureTime().equals(flight.getArrivalTime())
                || flight.getDepartureTime().isAfter(flight.getArrivalTime());
    }

}



