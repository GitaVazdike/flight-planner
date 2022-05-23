package io.codelex.flightplanner.services;

import io.codelex.flightplanner.api.*;
import io.codelex.flightplanner.database.AirportDatabaseRepository;
import io.codelex.flightplanner.database.FlightDatabaseRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@ConditionalOnProperty(prefix = "flight-planner", name = "store-type", havingValue = "database")
public class FlightServiceDatabaseImpl extends AbstractFlightService implements FlightService {

    private final FlightDatabaseRepository flightDatabaseRepository;
    private final AirportDatabaseRepository airportDatabaseRepository;

    public FlightServiceDatabaseImpl(FlightDatabaseRepository flightDatabaseRepository, AirportDatabaseRepository airportDatabaseRepository) {
        this.flightDatabaseRepository = flightDatabaseRepository;
        this.airportDatabaseRepository = airportDatabaseRepository;
    }

    @Override
    public void clearFlights() {
        flightDatabaseRepository.deleteAll();
    }

    @Override
    public Flight addFlight(AddFlightRequest addFlightRequest) {
        Flight flight = new Flight(addFlightRequest);

        if (flight.getFrom().isTheSameAirport(addFlightRequest) || isInvalidDate(flight)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return flightDatabaseRepository.save(flight);
    }

    @Override
    public void deleteFlightById(long id) {
//        flightDatabaseRepository.deleteById(id); //pirms tam pārbaudīt, vai eksistē
    }

    @Override
    public Flight searchFlightById(long id) {
        return null;
    }

    @Override
    public List<Airport> getAirport(String search) {
        return null;
    }

    @Override
    public PageResult searchFlight(SearchFlightsRequest searchFlightsRequest) {
        return null;
    }
}
