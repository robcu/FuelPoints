package org.FuelPoints.services;

import org.FuelPoints.entities.User;
import org.FuelPoints.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {
    ArrayList<Vehicle> findAllByUser(User user);
}
