package org.FuelPoints.controllers;

import org.FuelPoints.services.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {
    @Autowired
    TripRepository trips;


}
