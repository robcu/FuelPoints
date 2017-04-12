package org.FuelPoints.clients;

import org.FuelPoints.entities.Trip;
import org.FuelPoints.vessels.googlemaps.DirectionsResult;
import org.FuelPoints.vessels.googlemaps.Route;
import org.springframework.web.client.RestTemplate;

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

    public static void convertDirectionsResultToTrip(DirectionsResult result){
        //result.getRoutes().get(0).getLeg().getDistance();
        for(Route route : result.getRoutes()){

            //todo: conversion
        }

        return ;
    }
}
