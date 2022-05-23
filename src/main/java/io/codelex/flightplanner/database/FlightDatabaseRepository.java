package io.codelex.flightplanner.database;

import io.codelex.flightplanner.api.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightDatabaseRepository extends JpaRepository <Flight, Long> {

}
