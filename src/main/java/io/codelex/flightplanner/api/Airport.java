package io.codelex.flightplanner.api;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Airport {
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String airport;

    public Airport(String country, String city, String airport) {
        this.country = country;
        this.city = city;
        this.airport = airport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public boolean matchesSearchedPhrase(String search) {
        String formattedSearchString = search.trim().toUpperCase();

        return getCountry().toUpperCase().contains(formattedSearchString)
                || getCity().toUpperCase().contains(formattedSearchString)
                || getAirport().toUpperCase().contains(formattedSearchString);
    }

    public boolean isTheSameAirport(AddFlightRequest addFlightRequest) {
        return addFlightRequest.getFrom().getAirport().trim().equalsIgnoreCase(addFlightRequest.getTo().getAirport().trim())
                && addFlightRequest.getFrom().getCity().trim().equalsIgnoreCase(addFlightRequest.getTo().getCity().trim())
                && addFlightRequest.getFrom().getCountry().trim().equalsIgnoreCase(addFlightRequest.getTo().getCountry().trim());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;
        Airport airport1 = (Airport) o;
        return Objects.equals(getCountry(), airport1.getCountry())
                && Objects.equals(getCity(), airport1.getCity())
                && Objects.equals(getAirport(), airport1.getAirport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getCity(), getAirport());
    }

}
