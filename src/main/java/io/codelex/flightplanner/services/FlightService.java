package io.codelex.flightplanner.services;

import io.codelex.flightplanner.api.*;

import java.util.List;

public interface FlightService {

    void clearFlights();

    Flight addFlight(AddFlightRequest addFlightRequest);

    void deleteFlightById(long id);

    Flight searchFlightById(long id);

    List<Airport> getAirport(String search);

    PageResult searchFlight(SearchFlightsRequest searchFlightsRequest);

}
