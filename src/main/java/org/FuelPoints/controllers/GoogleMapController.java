package org.FuelPoints.controllers;

import org.FuelPoints.services.TripRepository;
import org.FuelPoints.vessels.googlemaps.DirectionsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.FuelPoints.clients.GoogleMaps.retrieveDirections;

@CrossOrigin(origins = "*") //TODO: Lock down to deployed prod domain
@RestController
public class GoogleMapController {
    @Autowired
    TripRepository trips;

    @RequestMapping(path = "/direction-results", method = RequestMethod.POST)
    public DirectionsResult findDirections(HttpServletResponse response, @RequestParam(value = "origin") String origin,
                              @RequestParam(value = "destination") String destination,
                                           @RequestParam(value = "price") Float price) throws IOException {

        //todo: what to do with price... send to

        return retrieveDirections(origin, destination);
    }
}
