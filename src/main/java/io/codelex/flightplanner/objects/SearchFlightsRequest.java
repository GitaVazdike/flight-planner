package io.codelex.flightplanner.objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SearchFlightsRequest {
    @Valid
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private String departureDate;

    public SearchFlightsRequest(String from, String to, String departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

}
