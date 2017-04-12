package org.FuelPoints.vessels.googlemaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Leg {

    Distance distance;
    Duration duration;
    End_address end_address;
    Start_address start_address;

    public Leg() {
    }

    public Leg(Distance distance, Duration duration, End_address end_address, Start_address start_address) {
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

    public End_address getEnd_address() {
        return end_address;
    }

    public void setEnd_address(End_address end_address) {
        this.end_address = end_address;
    }

    public Start_address getStart_address() {
        return start_address;
    }

    public void setStart_address(Start_address start_address) {
        this.start_address = start_address;
    }
}
