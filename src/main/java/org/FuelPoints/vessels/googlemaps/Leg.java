package org.FuelPoints.vessels.googlemaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Leg {

    Distance distance;
    Duration duration;
    String end_address;
    String start_address;

    public Leg() {
    }

    public Leg(Distance distance, Duration duration, String end_address, String start_address) {
        this.distance = distance;
        this.duration = duration;
        this.end_address = end_address;
        this.start_address = start_address;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }
}
