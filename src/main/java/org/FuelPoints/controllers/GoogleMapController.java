package org.FuelPoints.controllers;

import com.google.gson.Gson;
import org.FuelPoints.clients.GoogleMaps;
import org.FuelPoints.entities.Trip;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.services.TripRepository;
import org.FuelPoints.services.VehicleRepository;
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
    public String findDirections(HttpServletResponse response, @RequestParam(value = "origin") String origin,
                                 @RequestParam(value = "destination") String destination,
                                 @RequestParam(value = "price") Float price) throws IOException {

        String json = retrieveJsonDirections(origin, destination);
        Gson gson = new Gson();
        DirectionsResult directionsResult = gson.fromJson(json, DirectionsResult.class);
        ArrayList<Trip> trips = GoogleMaps.convertDirectionsResultToTrips(directionsResult);

        ArrayList<Vehicle> vehiclesList = new ArrayList<>();
        vehicles.
        for (Trip trip : trips) {
            trip.setVehicle();
            trip.setFuelGallonPrice(price);

        }

        //todo: return json string, and list of trips with one vehicle.

        //todo: create masterUser who has 3 vehicles so the vehicles are already in DB and have a userid (no null error)


        //i want to return a serialized class containing the json string, plus my extra data. a DirectionResponse
        //the extra data is for the table:
        return retrieveJsonDirections(origin, destination);
    }

}
