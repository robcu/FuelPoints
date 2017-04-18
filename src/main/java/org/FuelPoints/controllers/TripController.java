package org.FuelPoints.controllers;

import org.FuelPoints.entities.Trip;
import org.FuelPoints.entities.User;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.services.TripRepository;
import org.FuelPoints.services.UserRepository;
import org.FuelPoints.services.VehicleRepository;
import org.FuelPoints.utilities.serializers.RootSerializer;
import org.FuelPoints.utilities.serializers.TripSerializer;
import org.FuelPoints.vessels.googlemaps.DirectionsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

import static org.FuelPoints.clients.GoogleMaps.convertDirectionsResultToTrips;

@CrossOrigin(origins = "*") //TODO: Lock down to deployed prod domain
@RestController
public class TripController {
    @Autowired
    VehicleRepository vehicles;
    @Autowired
    UserRepository users;
    @Autowired
    TripRepository trips;

    RootSerializer rootSerializer = new RootSerializer();
    TripSerializer tripSerializer = new TripSerializer();


    @RequestMapping(path = "/trips", method = RequestMethod.POST)
    public HashMap<String, Object> addTrip(HttpServletResponse response, @RequestBody DirectionsResult directionsResult,
                                           @RequestParam(value = "vehicleId") String vehicleId,
                                           @RequestParam(value = "tripIndex") Integer tripIndex,
                                           @RequestParam(value = "price") Float price) {

        //todo: i think this route will take the json string directions and need to be parsed into a DR.

        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByName(u.getName());

        Trip trip = convertDirectionsResultToTrips(directionsResult).get(tripIndex);
        trip.setUser(user);

        Vehicle vehicle = vehicles.findOne(vehicleId);
        trip.setVehicle(vehicle);

        trip.setFuelGallonPrice(price);

        trips.save(trip);

        return rootSerializer.serializeOne(
                "/trips/" + trip.getId(),
                trip,
                tripSerializer);
    }

    @RequestMapping(path = "/trips", method = RequestMethod.GET)
    public HashMap<String, Object> retrieveTrip(HttpServletResponse response,
                                                @RequestParam(value = "tripId") String tripId) {
        Trip trip = trips.findOne(tripId);

        return rootSerializer.serializeOne(
                "/trips/" + trip.getId(),
                trip,
                tripSerializer);
    }

    @RequestMapping(path = "/trips/vehicle", method = RequestMethod.GET)
    public HashMap<String, Object> retrieveVehiclesTrips(HttpServletResponse response,
                                                         @RequestParam(value = "vehicleId") String vehicleId) {
        Vehicle vehicle = vehicles.findOne(vehicleId);
        ArrayList<Trip> listOfTrips = trips.findAllByVehicle(vehicle);

        return rootSerializer.serializeMany(
                "/trips/" + vehicleId,
                listOfTrips,
                tripSerializer);
    }

    @RequestMapping(path = "/trips/user", method = RequestMethod.GET)
    public HashMap<String, Object> retrieveUsersTrips(HttpServletResponse response) {

        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByName(u.getName());

        ArrayList<Trip> listOfTrips = trips.findAllByUser(user);

        return rootSerializer.serializeMany(
                "/trips/" + user.getId(),
                listOfTrips,
                tripSerializer);
    }

    @RequestMapping(path = "/trips/global", method = RequestMethod.GET)
    public HashMap<String, Object> retrieveALLTrips(HttpServletResponse response) {
        ArrayList<Trip> listOfTrips = (ArrayList<Trip>) trips.findAll();

        return rootSerializer.serializeMany(
                "/trips/" + "",
                listOfTrips,
                tripSerializer);
    }

    @RequestMapping(path = "/trips", method = RequestMethod.DELETE)
    public void deleteTrip(HttpServletResponse response,
                           @RequestParam(value = "tripId") String tripId) {
        trips.delete(tripId);
        //todo: add setStatus

    }
}
