package io.codelex.flightplanner.services;

import io.codelex.flightplanner.api.Flight;

public abstract class AbstractFlightService {
    protected boolean isInvalidDate(Flight flight) {
        return flight.getDepartureTime().equals(flight.getArrivalTime())
                || flight.getDepartureTime().isAfter(flight.getArrivalTime());
    }
}
