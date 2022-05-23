package io.codelex.flightplanner.database;

import io.codelex.flightplanner.api.Airport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
//@Transactional
@ConditionalOnProperty(prefix = "flight-planner", name = "store-type", havingValue = "database")
public class AirportDatabaseService {
    private final AirportDatabaseRepository airportRepository;

    public AirportDatabaseService(AirportDatabaseRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public void addAirport(Airport airport) {
        airportRepository.save(airport);
    }
}
