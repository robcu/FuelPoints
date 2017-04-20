package org.FuelPoints.controllers;

import org.FuelPoints.FuelPointsApplication;
import org.FuelPoints.entities.User;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.services.UserRepository;
import org.FuelPoints.services.VehicleRepository;
import org.FuelPoints.utilities.serializers.DataListSerializer;
import org.FuelPoints.utilities.serializers.RootSerializer;
import org.FuelPoints.utilities.serializers.VehicleSerializer;
import org.FuelPoints.vessels.MenuItem;
import org.FuelPoints.vessels.XMLVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.FuelPoints.utilities.parsers.RootParser;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

    @RequestMapping(path = "/vehicles", method = RequestMethod.POST)
    public HashMap<String, Object> addVehicle(HttpServletResponse response, @RequestBody RootParser<Vehicle> parser) throws IOException {

        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByName(u.getName());
        String feId = parser.getData().getEntity().getFuelEconomyId();

        XMLVehicle xmlVehicle = retrieveXMLVehicle(feId);

        String optionText = "";
        for (MenuItem option : user.getOptionsCache().getMenuItems()) {
            if (option.getId().equals(feId)) optionText = option.getText();
        }

        Vehicle vehicle = new Vehicle(xmlVehicle.getYear(), xmlVehicle.getMake(), xmlVehicle.getModel(),
                optionText, feId, xmlVehicle.getCityMPG(), xmlVehicle.getHwyMPG(), xmlVehicle.getCombMPG(), user);
        vehicles.save(vehicle);

        return rootSerializer.serializeOne(
                "/vehicles/" + vehicle.getId(),
                vehicle,
                vehicleSerializer);
    }

    @RequestMapping(path = "/vehicles", method = RequestMethod.GET)
    public HashMap<String, Object> retrieveVehicleList(HttpServletResponse response) {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByName(u.getName());
        ArrayList<Vehicle> usersVehicles = vehicles.findAllByUser(user);

        return rootSerializer.serializeMany(
                "/vehicles/" + user.getId(),
                usersVehicles,
                vehicleSerializer);
    }

    @RequestMapping(path = "/vehicles", method = RequestMethod.DELETE)
    public void deleteVehicle(HttpServletResponse response, @RequestParam(value = "vehicleId") String vehicleId) throws IOException {
        Vehicle vehicle = vehicles.findOne(vehicleId);
        if (vehicle != null) {
            vehicles.delete(vehicleId);
            response.setStatus(204);
        } else {
            response.sendError(400, "Error deleting vehicle.");
        }
    }

}
