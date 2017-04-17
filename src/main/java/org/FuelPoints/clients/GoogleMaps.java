package org.FuelPoints.clients;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.FuelPoints.entities.Trip;
import org.FuelPoints.vessels.googlemaps.DirectionsResult;
import org.FuelPoints.vessels.googlemaps.Route;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class GoogleMaps {

    private static String YOUR_API_KEY = "";
    private static String BASE_URL = "https://maps.googleapis.com/maps/api/directions/json?origin=";

    public static DirectionsResult retrieveDirections(String origin, String destination, String waypoints) {

        RestTemplate restTemplate = new RestTemplate();
        DirectionsResult result = restTemplate.getForObject(BASE_URL + origin + "&destination=" + destination + "&waypoints=" + waypoints + "&key=" + YOUR_API_KEY, DirectionsResult.class);

        //System.out.println(result.getRoutes().get(0).getSummary());

        return result;
    }

// A trip is a route that a User has chosen.
// This method should be called when a User saves a Route to their history.

    public static ArrayList<Trip> convertDirectionsResultToTrips(DirectionsResult result) {
        ArrayList<Trip> listOfTrips = new ArrayList<>();
        Trip trip = new Trip();
        for (Route route : result.getRoutes()) {
            trip.setOrigin(route.getLeg().getStart_address().toString());
            trip.setDestination(route.getLeg().getEnd_address().toString());
            trip.setTotalDistance(route.getLeg().getDistance().getValue());
            trip.setTotalDuration(route.getLeg().getDuration().getValue());
            listOfTrips.add(trip);
        }
        return listOfTrips;
    }


}
