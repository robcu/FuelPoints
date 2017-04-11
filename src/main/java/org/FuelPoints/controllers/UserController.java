package org.FuelPoints.controllers;

import org.FuelPoints.utilities.parsers.RootParser;
import org.FuelPoints.utilities.serializers.RootSerializer;
import org.FuelPoints.utilities.serializers.UserSerializer;
import org.FuelPoints.vessels.JsonUser;
import org.FuelPoints.entities.User;
import org.FuelPoints.services.TripRepository;
import org.FuelPoints.services.UserRepository;
import org.FuelPoints.services.VehicleRepository;
import org.FuelPoints.utilities.PasswordStorage;
import org.FuelPoints.vessels.MenuItem;
import org.FuelPoints.vessels.XMLVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.FuelPoints.clients.FuelEconomy.retrieveList;
import static org.FuelPoints.clients.FuelEconomy.retrieveOptionsAndVehicleNumber;
import static org.FuelPoints.clients.FuelEconomy.retrieveXMLVehicle;


@RestController
public class UserController {
    @Autowired
    UserRepository users;
    @Autowired
    VehicleRepository vehicles;
    @Autowired
    TripRepository trips;


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(HttpServletResponse response, @RequestBody RootParser<JsonUser> jsonUser) throws Exception {

        User user = users.findFirstByName(jsonUser.getData().getEntity().getName());
        if (user == null) {
            response.sendError(401, "Account does not exist.");
        } else if (!user.verifyPassword(jsonUser.getData().getEntity().getPassword())) {
            response.sendError(401, "Invalid credentials");
        }
        return user;
    }


    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public HashMap<String, Object> register(HttpServletResponse response, @RequestBody RootParser<JsonUser> jsonUser) throws IOException, PasswordStorage.CannotPerformOperationException {

        User user = users.findFirstByName(jsonUser.getData().getEntity().getName());
        if (user != null) {
            response.sendError(401, "Username is not available.");
        } else {
            user = new User(jsonUser.getData().getEntity().getName(), jsonUser.getData().getEntity().getPassword());
            users.save(user);
            response.sendError(201, "User successfully created.");
        }
        return RootSerializer.serializeOne(
                "/users/" + user.getId(),
                user,
                UserSerializer.class);

    }



}



