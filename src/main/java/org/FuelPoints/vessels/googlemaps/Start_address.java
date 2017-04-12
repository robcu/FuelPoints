package org.FuelPoints.vessels.googlemaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Start_address {
    String start_address;

    public Start_address() {
    }

    public Start_address(String start_address) {
        this.start_address = start_address;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }
}
