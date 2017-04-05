package org.FuelPoints.controllers;

import org.FuelPoints.entities.User;
import org.FuelPoints.services.UserRepository;
import org.FuelPoints.services.VehicleRepository;
import org.FuelPoints.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class FuelPointsController {
    @Autowired
    UserRepository users;
    @Autowired
    VehicleRepository vehicles;


    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String home() {

        return "hello";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(HttpServletResponse response, String username, String password) throws Exception {
        User user = users.findFirstByName(username);
        if (user == null) {
            response.sendError(401, "Account does not exist.");
        } else if (!user.verifyPassword(password)) {
            response.sendError(401, "Invalid credentials.");
        }
        return user;
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(HttpServletResponse response) {

    }


    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public void register(HttpServletResponse response, String username, String password) throws IOException, PasswordStorage.CannotPerformOperationException {
        User user = users.findFirstByName(username);
        if (user != null) {
            response.sendError(401, "Username is not available.");
        } else {
            users.save(new User(username, password));
            response.sendError(201, "User successfully created.");
        }
    }
}

