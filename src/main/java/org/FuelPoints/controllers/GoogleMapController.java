package org.FuelPoints.controllers;

import com.google.gson.Gson;
import org.FuelPoints.FuelPointsApplication;
import org.FuelPoints.clients.GoogleMaps;
import org.FuelPoints.entities.Trip;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.services.TripRepository;
import org.FuelPoints.services.VehicleRepository;
import org.FuelPoints.vessels.DirectionResponse;
import org.FuelPoints.vessels.googlemaps.DirectionsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.FuelPoints.clients.GoogleMaps.retrieveJsonDirections;

@CrossOrigin(origins = "*") //TODO: Lock down to deployed prod domain
@RestController
public class GoogleMapController {
    @Autowired
    TripRepository trips;
    @Autowired
    VehicleRepository vehicles;

    @RequestMapping(path = "/direction-results", method = RequestMethod.POST)
    public DirectionResponse findDirectionsForAnonymous(HttpServletResponse response, @RequestParam(value = "origin") String origin,
                                            @RequestParam(value = "destination") String destination,
                                            @RequestParam(value = "price") Float price) throws IOException {
//        char d = 'd';
//        for (char c : origin.toCharArray()) {
//            if (c == ',') {
//                d = ',';
//            }
//            if (c == ' ' && d == ',') {
//                c = '+';
//                d = 'd';
//            }
//        }

        Object json = retrieveJsonDirections(origin, destination);
        Gson gson = new Gson();
        String convertedJson = gson.toJson(json);
        DirectionsResult directionsResult = gson.fromJson(convertedJson, DirectionsResult.class);
        ArrayList<Trip> trips = GoogleMaps.convertDirectionsResultToTrips(directionsResult);

        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setJson(convertedJson);
        ArrayList<Vehicle> localVehicleArrayList = FuelPointsApplication.vehicleArrayList;

        for (Trip trip : trips) {
            Trip temp = new Trip(trip);
            temp.setVehicle(localVehicleArrayList.get(0));
            temp.setFuelGallonPrice(price);
            directionResponse.addToOne(temp);

            Trip temp2 = new Trip(trip);
            temp2.setVehicle(localVehicleArrayList.get(1));
            temp2.setFuelGallonPrice(price);
            directionResponse.addToTwo(temp2);

            Trip temp3 = new Trip(trip);
            temp3.setVehicle(localVehicleArrayList.get(2));
            temp3.setFuelGallonPrice(price);
            directionResponse.addToThree(temp3);

        }

        return directionResponse;
    }

    //todo: route above works for someone not logged in. need to build route for logged in user or modify above
}
