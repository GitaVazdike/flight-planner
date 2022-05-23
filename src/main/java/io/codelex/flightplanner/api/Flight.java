package io.codelex.flightplanner.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class Flight {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private Airport from;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private Airport to;

    private String carrier;

//    @Temporal(TemporalType.TIMESTAMP)
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    @Transient
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Flight(AddFlightRequest addFlightRequest) {
//        this.id = id;
        from = addFlightRequest.getFrom();
        to = addFlightRequest.getTo();
        carrier = addFlightRequest.getCarrier();
        departureTime = LocalDateTime.parse(addFlightRequest.getDepartureTime(), formatter);
        arrivalTime = LocalDateTime.parse(addFlightRequest.getArrivalTime(), formatter);
    }

    public Flight() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean matchesSearchRequest(SearchFlightsRequest searchFlightsRequest) {

        return getFrom().getAirport().equals(searchFlightsRequest.getFrom())
                && getTo().getAirport().equals(searchFlightsRequest.getTo())
                && departureTime.toLocalDate().equals(searchFlightsRequest.getDepartureDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return Objects.equals(getFrom(), flight.getFrom()) && Objects.equals(getTo(), flight.getTo()) && Objects.equals(getCarrier(), flight.getCarrier()) && Objects.equals(getDepartureTime(), flight.getDepartureTime()) && Objects.equals(getArrivalTime(), flight.getArrivalTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo(), getCarrier(), getDepartureTime(), getArrivalTime());
    }

}



