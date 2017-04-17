package org.FuelPoints.controllers;

import org.FuelPoints.services.TripRepository;
import org.FuelPoints.services.UserRepository;
import org.FuelPoints.services.VehicleRepository;
import org.FuelPoints.utilities.serializers.RootSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

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
//todo: create TripSerializer


//    @RequestMapping(path = "/trips", method = RequestMethod.POST)
//    public HashMap<String, Object> addVehicle(HttpServletResponse response){
//
//
//
//        return rootSerializer.serializeOne(
//                "/vehicles/" + trip.getId(),
//                trip,
//                tripSerializer);
//    }
//
//    @RequestMapping(path = "/trips", method = RequestMethod.GET)
//    public HashMap<String, Object> addVehicle(HttpServletResponse response){
//
//
//
//        return rootSerializer.serializeOne(
//                "/vehicles/" + trip.getId(),
//                trip,
//                tripSerializer);
//    }
//
//    @RequestMapping(path = "/trips", method = RequestMethod.GET)
//    public HashMap<String, Object> addVehicle(HttpServletResponse response){
//
//
//
//        return rootSerializer.serializeMany(
//                "/vehicles/" + "",
//                listOfTrips,
//                tripSerializer);
//    }
}
