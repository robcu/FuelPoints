package org.FuelPoints.vessels.googlemaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {
    ArrayList<Leg> legs;
    String summary;

    public Route() {
    }

    public Route(ArrayList<Leg> legs, String summary) {
        this.legs = legs;
        this.summary = summary;
    }

    public ArrayList<Leg> getLegs() {
        return legs;
    }

    public void setLegs(ArrayList<Leg> legs) {
        this.legs = legs;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
