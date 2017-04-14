package org.FuelPoints.controllers;

import org.FuelPoints.entities.User;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.services.UserRepository;
import org.FuelPoints.services.VehicleRepository;
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

import static org.FuelPoints.clients.FuelEconomy.retrieveXMLVehicle;

@CrossOrigin(origins = "*") //TODO: Lock down to deployed prod domain
@RestController
public class VehicleController {
    @Autowired
    VehicleRepository vehicles;
    @Autowired
    UserRepository users;

    RootSerializer rootSerializer = new RootSerializer();
    VehicleSerializer vehicleSerializer = new VehicleSerializer();
    DataListSerializer dataListSerializer = new DataListSerializer();

    @RequestMapping(path = "/vehicles", method = RequestMethod.POST)
    public HashMap<String, Object> addVehicle(HttpServletResponse response, @RequestParam(value = "vehicleId") String vehicleId, @RequestParam(value = "userId") String userId) throws IOException {

        User user = users.findOne(userId);
        XMLVehicle xmlVehicle = retrieveXMLVehicle(vehicleId);    //TODO: the vehicle record page does not contain a field "option". How to get it here? Do I need it?

      Vehicle vehicle = new Vehicle(xmlVehicle.getYear(), xmlVehicle.getMake(), xmlVehicle.getModel(), vehicleId, user);
      vehicles.save(vehicle);
      //response.sendError(201, "Vehicle added.");

        return rootSerializer.serializeOne(
                "/vehicles/" + vehicle.getId(),
                vehicle,
                vehicleSerializer);
    }

    @RequestMapping(path = "/vehicles/{id}", method = RequestMethod.GET)
    public void retrieveVehiclesList(HttpServletResponse response, @PathVariable String id)
    {
        //todo: return a users vehicles
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

    //todo: add route that returns a list of a users vehicles

}
