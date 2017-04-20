package org.FuelPoints.controllers;

import com.google.gson.Gson;
import org.FuelPoints.FuelPointsApplication;
import org.FuelPoints.clients.GoogleMaps;
import org.FuelPoints.entities.Trip;
import org.FuelPoints.entities.User;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.services.TripRepository;
import org.FuelPoints.services.UserRepository;
import org.FuelPoints.services.VehicleRepository;
import org.FuelPoints.utilities.serializers.DirectionResponseSerializer;
import org.FuelPoints.utilities.serializers.RootSerializer;
import org.FuelPoints.vessels.DirectionResponse;
import org.FuelPoints.vessels.googlemaps.DirectionsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.FuelPoints.clients.GoogleMaps.retrieveJsonDirections;

@CrossOrigin(origins = "*") //TODO: Lock down to deployed prod domain
@RestController
public class DirectionsController {
    @Autowired
    TripRepository trips;
    @Autowired
    VehicleRepository vehicles;
    @Autowired
    UserRepository users;

    RootSerializer rootSerializer = new RootSerializer();
    DirectionResponseSerializer directionResponseSerializer = new DirectionResponseSerializer();

    @RequestMapping(path = "/direction-responses", method = RequestMethod.GET)
    public HashMap<String, Object> findDirections(HttpServletResponse response, @RequestParam(value = "origin") String origin,
                                                  @RequestParam(value = "destination") String destination,
                                                  @RequestParam(value = "price") Float price) throws IOException {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();

        if (u != null) {
            return findDirectionsForRegisteredUser(response, origin, destination, price);
        }

        return findDirectionsForAnonymous(response, origin, destination, price);
    }

    public HashMap<String, Object> findDirectionsForAnonymous(HttpServletResponse response, String origin, String destination, Float price) throws IOException {

        Object json = retrieveJsonDirections(origin, destination);
        Gson gson = new Gson();
        String jsonString = gson.toJson(json);
        DirectionsResult directionsResult = gson.fromJson(jsonString, DirectionsResult.class);
        ArrayList<Trip> trips = GoogleMaps.convertDirectionsResultToTrips(directionsResult);

        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setJson(json);
        ArrayList<Vehicle> localVehicleArrayList = FuelPointsApplication.vehicleArrayList;

        for (Trip trip : trips) {
            Trip temp0 = new Trip(trip);
            temp0.setVehicle(localVehicleArrayList.get(0));
            temp0.setFuelGallonPrice(price);
            directionResponse.addToOne(temp0);

            Trip temp1 = new Trip(trip);
            temp1.setVehicle(localVehicleArrayList.get(1));
            temp1.setFuelGallonPrice(price);
            directionResponse.addToTwo(temp1);

            Trip temp2 = new Trip(trip);
            temp2.setVehicle(localVehicleArrayList.get(2));
            temp2.setFuelGallonPrice(price);
            directionResponse.addToThree(temp2);
        }

        return rootSerializer.serializeOne(
                "/directions-results?destination=" + destination + "&origin=" + origin + "&price=" + price,
                directionResponse,
                directionResponseSerializer);
    }

    public HashMap<String, Object> findDirectionsForRegisteredUser(HttpServletResponse response, String origin, String destination, Float price) throws IOException {

        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByName(u.getName());
        ArrayList<Vehicle> usersVehicles = vehicles.findAllByUser(user);

        Object json = retrieveJsonDirections(origin, destination);
        Gson gson = new Gson();
        String jsonString = gson.toJson(json);
        DirectionsResult directionsResult = gson.fromJson(jsonString, DirectionsResult.class);
        ArrayList<Trip> trips = GoogleMaps.convertDirectionsResultToTrips(directionsResult);
        user.setTripCache(trips);

        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setJson(json);

        for (Trip trip : trips) {
            for (Vehicle vehicle : usersVehicles) {
                Trip newTrip = new Trip(trip);
                newTrip.setVehicle(vehicle);
                newTrip.setFuelGallonPrice(price);      //todo: currently putting all vehicle/trips in one array
                directionResponse.addToOne(newTrip);    //todo: restructure directionresponse to arrange by trip?
            }
        }

        return rootSerializer.serializeOne(
                "/directions-results?destination=" + destination + "&origin=" + origin + "&price=" + price,
                directionResponse,
                directionResponseSerializer);
    }
}
