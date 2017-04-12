package org.FuelPoints.controllers;

import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.services.VehicleRepository;
import org.FuelPoints.utilities.DataList;
import org.FuelPoints.utilities.parsers.RootParser;
import org.FuelPoints.utilities.serializers.DataListSerializer;
import org.FuelPoints.utilities.serializers.RootSerializer;
import org.FuelPoints.utilities.serializers.VehicleSerializer;
import org.FuelPoints.vessels.XMLVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static org.FuelPoints.clients.FuelEconomy.retrieveList;
import static org.FuelPoints.clients.FuelEconomy.retrieveOptionsAndVehicleNumbers;
import static org.FuelPoints.clients.FuelEconomy.retrieveXMLVehicle;

@CrossOrigin(origins = "*") //TODO: Lock down to deployed prod domain
@RestController
public class VehiclesController {
    @Autowired
    VehicleRepository vehicles;

    RootSerializer rootSerializer = new RootSerializer();
    VehicleSerializer vehicleSerializer = new VehicleSerializer();
    DataListSerializer dataListSerializer = new DataListSerializer();

    @RequestMapping(path = "/vehicles", method = RequestMethod.POST)
    public HashMap<String, Object> addVehicle(HttpServletResponse response, @RequestBody RootParser<Vehicle> parser) throws IOException {
        Vehicle vehicle = new Vehicle();
        //todo: i need "user" from front

        XMLVehicle xmlVehicle = retrieveXMLVehicle(parser.getData().getId());

        //todo: save vehicle
//      vehicles.save(new Vehicle(vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getUser()));
//      vehicles.save(vehicle);
        response.sendError(201, "Vehicle added.");

        return rootSerializer.serializeOne(
                "/vehicles/" + vehicle.getId(),
                vehicle,
                vehicleSerializer);
    }

    @RequestMapping(path = "/vehicles/{id}", method = RequestMethod.DELETE)
    public void deleteVehicle(HttpServletResponse response, @PathVariable String id) throws IOException {
        Vehicle vehicle = vehicles.findOne(id);
        if (vehicle != null) {
            vehicles.delete(id);
            response.setStatus(204);
        } else {
            response.sendError(400, "Error deleting vehicle.");
        }
    }



}
