package org.FuelPoints.services;

import org.FuelPoints.entities.Trip;
import org.FuelPoints.entities.User;
import org.FuelPoints.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TripRepository extends CrudRepository<Trip, String> {
    ArrayList<Trip> findAllByUser (User user);
    ArrayList<Trip> findAllByVehicle (Vehicle vehicle);
}
