package org.FuelPoints.vessels.googlemaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {
    Leg leg;
    String summary;

    public Route(Leg leg, String summary) {
        this.leg = leg;
        this.summary = summary;
    }

    public Route() {

    }

    public Leg getLeg() {
        return leg;
    }

    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
