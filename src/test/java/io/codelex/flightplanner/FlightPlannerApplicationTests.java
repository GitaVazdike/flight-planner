package io.codelex.flightplanner;
import io.codelex.flightplanner.api.AddFlightRequest;
import io.codelex.flightplanner.api.Airport;
import io.codelex.flightplanner.api.Flight;
import io.codelex.flightplanner.controllers.AdminController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FlightPlannerApplicationTests {

	@Autowired
	AdminController adminController;

	@Test
	void addFlightTest() {
		Airport from = new Airport("Latvia", "Riga", "RIX");
		Airport to = new Airport("Dubai", "United Arab Emirates", "DBX");
		String carrier = "AirBaltic";
		LocalDateTime departureTime = LocalDateTime.of(2022, 5, 2, 8, 50);
		LocalDateTime arrivalTime = LocalDateTime.of(2022, 5, 02, 11, 55);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");

		AddFlightRequest addFlightRequest = new AddFlightRequest(from, to, carrier, formatter.format(departureTime), formatter.format(arrivalTime));

		Flight flight = adminController.addFlight(addFlightRequest);

		assertNotNull(flight.getId());
		assertEquals(flight.getFrom(), from);
		assertEquals(flight.getTo(), to);
		assertEquals(flight.getCarrier(), carrier);
		assertEquals(flight.getDepartureTime(), departureTime);
		assertEquals(flight.getArrivalTime(), arrivalTime);
	}

}
