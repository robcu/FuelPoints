package org.FuelPoints.services;

import org.FuelPoints.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {

}
