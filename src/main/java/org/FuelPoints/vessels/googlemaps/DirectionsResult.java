package org.FuelPoints.vessels.googlemaps;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionsResult {

    ArrayList<Route> routes;

    String status;

    public DirectionsResult() {
    }

    public DirectionsResult(ArrayList<Route> routes, String status) {
        this.routes = routes;
        this.status = status;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
