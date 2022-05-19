package io.codelex.flightplanner.services;

import io.codelex.flightplanner.api.AddFlightRequest;
import io.codelex.flightplanner.api.Airport;
import io.codelex.flightplanner.api.Flight;
import io.codelex.flightplanner.database.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock
    FlightRepository flightRepository;

    @InjectMocks
    FlightService flightService;

    @Test
    public void TestAddFlight() {
        Airport from = new Airport("Latvia", "Riga", "RIX");
        Airport to = new Airport("Dubai", "United Arab Emirates", "DBX");
        String carrier = "AirBaltic";
        String departureTime = "2022-05-02 08:50";
        String arrivalTime = "2022-05-02 11:55";

        AddFlightRequest addFlightRequest = new AddFlightRequest(from, to, carrier, departureTime, arrivalTime);

        Mockito.doAnswer(invocation -> {
            AddFlightRequest request = invocation.getArgument(0);
            Assertions.assertEquals(addFlightRequest, request);
            return new Flight(10L, request);
        }).when(flightRepository).addFlight(addFlightRequest);

        Flight flight = flightService.addFlight(addFlightRequest);

        Mockito.verify(flightRepository).addFlight(addFlightRequest);
    }
}
