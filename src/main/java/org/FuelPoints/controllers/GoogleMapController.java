package org.FuelPoints.controllers;

import org.FuelPoints.services.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") //TODO: Lock down to deployed prod domain
@RestController
public class GoogleMapController {
    @Autowired
    TripRepository trips;


}
