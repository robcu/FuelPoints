package org.FuelPoints.controllers;

import org.FuelPoints.entities.JsonUser;
import org.FuelPoints.entities.User;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.services.TripRepository;
import org.FuelPoints.services.UserRepository;
import org.FuelPoints.services.VehicleRepository;
import org.FuelPoints.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jodd.json.JsonParser;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FuelPointsController {
    @Autowired
    UserRepository users;
    @Autowired
    VehicleRepository vehicles;
    @Autowired
    TripRepository trips;


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(HttpServletResponse response, @RequestBody String body) throws Exception {
        JsonParser p = new JsonParser();
        JsonUser jsonUser = p.parse(body, JsonUser.class);

        User user = users.findFirstByName(jsonUser.getName());
        if (user == null) {
            response.sendError(401, "Account does not exist.");
        } else if (!user.verifyPassword(jsonUser.getPassword())) {
            response.sendError(401, "Invalid credentials");
        }
        return user;
    }
//    @RequestMapping(path = "/logout", method = RequestMethod.POST)
//    public void logout(HttpServletResponse response) { }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public void register(HttpServletResponse response, @RequestBody String body) throws IOException, PasswordStorage.CannotPerformOperationException {
        JsonParser p = new JsonParser();
        JsonUser jsonUser = p.parse(body, JsonUser.class);

        User user = users.findFirstByName(jsonUser.getName());
        if (user != null) {
            response.sendError(401, "Username is not available.");
        } else {
            users.save(new User(jsonUser.getName(), jsonUser.getPassword()));
            response.sendError(201, "User successfully created.");
        }
    }

    @RequestMapping(path = "/add_vehicle", method = RequestMethod.POST)
    public void addVehicle(HttpServletResponse response, @RequestBody String body) throws IOException {
        JsonParser p = new JsonParser();
        Vehicle vehicle = p.parse(body, Vehicle.class);

        vehicles.save(new Vehicle(vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getUser()));
        response.sendError(201, "Vehicle added.");
    }

    @RequestMapping(path = "/delete_vehicle", method = RequestMethod.POST)
    public void deleteVehicle(HttpServletResponse response, @RequestBody String body) throws IOException {
        JsonParser p = new JsonParser();
        Integer id = p.parse(body, Integer.class);

        if (vehicles.exists(id)) {
            vehicles.delete(id);
            response.sendError(200, "Vehicle deleted");
        } else {
            response.sendError(400, "Error deleting vehicle.");
        }
    }

//    @RequestMapping(path = "/change_username", method = RequestMethod.POST)
//    public void changeUsername(HttpServletResponse response, @RequestBody String body){
//    }

//    @RequestMapping(path = "/change_password", method = RequestMethod.POST)
//    public void changePassword(HttpServletResponse response, @RequestBody String body) throws PasswordStorage.CannotPerformOperationException, PasswordStorage.InvalidHashException, IOException {
//        User user = users.findFirstByName(username);
//        if(user.verifyPassword(password)){
//            user.setPassword(newPassword);
//            users.save(user);
//            response.sendError(200, "Password updated.");
//        } else{
//            response.sendError(401, "Invalid credentials");
//        }
//    }

}

