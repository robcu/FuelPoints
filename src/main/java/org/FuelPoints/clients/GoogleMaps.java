package org.FuelPoints.clients;

import org.FuelPoints.vessels.googlemaps.DirectionsResult;
import org.FuelPoints.vessels.googlemaps.Route;
import org.springframework.web.client.RestTemplate;

public class GoogleMaps {

    public static void retrieveDirections() {
        String YOUR_API_KEY = "";
        RestTemplate restTemplate = new RestTemplate();
        DirectionsResult result = restTemplate.getForObject("https://maps.googleapis.com/maps/api/directions/json?origin=Chicago,IL&destination=Los+Angeles,CA&waypoints=Joplin,MO|Oklahoma+City,OK&key=" + YOUR_API_KEY, DirectionsResult.class);
        System.out.println(result.getRoutes().get(0).getSummary());

        //todo: reference concretepage tutorial to annotate json class to ignore stuff
    }
}
