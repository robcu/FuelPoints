package org.FuelPoints.controllers;

import org.FuelPoints.entities.User;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.services.UserRepository;
import org.FuelPoints.services.VehicleRepository;
import org.FuelPoints.utilities.serializers.DataListSerializer;
import org.FuelPoints.utilities.serializers.RootSerializer;
import org.FuelPoints.utilities.serializers.VehicleSerializer;
import org.FuelPoints.vessels.XMLVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    DataListSerializer dataListSerializer = new DataListSerializer();

    @RequestMapping(path = "/vehicles", method = RequestMethod.POST)
    public HashMap<String, Object> addVehicle(HttpServletResponse response,
                                              @RequestParam(value = "feId") String feId,
                                              @RequestParam(value = "optionIndex") Integer optionIndex) throws IOException {

        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByName(u.getName());

        XMLVehicle xmlVehicle = retrieveXMLVehicle(feId);

        //todo: i know the feid so i know the optionindex - i can redo the line below - filter, match, for each
        String option = user.getOptionsCache().getMenuItems().get(optionIndex).getText();

        Vehicle vehicle = new Vehicle(xmlVehicle.getYear(), xmlVehicle.getMake(), xmlVehicle.getModel(),
                option, feId, xmlVehicle.getCityMPG(), xmlVehicle.getHwyMPG(), xmlVehicle.getCombMPG(), user);
        vehicles.save(vehicle);
        //response.setStatus(201, "Vehicle added.");

        return rootSerializer.serializeOne(
                "/vehicles/" + vehicle.getId(),
                vehicle,
                vehicleSerializer);
    }

    //todo: add route to return ONE vehicle belonging to a user? useful for getting associated trips? use the vehicle id to search trips


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
