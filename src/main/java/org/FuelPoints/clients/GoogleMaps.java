package org.FuelPoints.clients;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.FuelPoints.entities.Trip;
import org.FuelPoints.vessels.googlemaps.DirectionsResult;
import org.FuelPoints.vessels.googlemaps.Route;
import org.springframework.web.client.RestTemplate;

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

//    public static void retrieveNativeList(String urlExtension)  {
//        RestTemplate restTemplate = new RestTemplate();
//
//        String json = "";
//        JsonParser parser = new JsonParser();
//        JsonObject rootObj = parser.parse(json).getAsJsonObject();
//        JsonObject locObj = rootObj.getAsJsonObject("result").getAsJsonObject("geometry").getAsJsonObject("location");
//
//        String status = rootObj.get("status").getAsString();
//        String lat = locObj.get("lat").getAsString();
//        String lng = locObj.get("lng").getAsString();
//
//        System.out.printf("Status: %s, Latitude: %s, Longitude: %s\n", status,
//                lat, lng);
//
//
//    }

// A trip is a route that a User has chosen.
// This method should be called when a User saves a Route to their history.

    public static void convertDirectionsResultToTrip(DirectionsResult result){
        //result.getRoutes().get(0).getLeg().getDistance();
        for(Route route : result.getRoutes()){

            //todo: conversion
        }


    }


}
