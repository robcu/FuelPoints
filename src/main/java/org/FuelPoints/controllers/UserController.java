package org.FuelPoints.controllers;

import org.FuelPoints.utilities.parsers.RootParser;
import org.FuelPoints.utilities.serializers.RootSerializer;
import org.FuelPoints.utilities.serializers.UserSerializer;
import org.FuelPoints.vessels.JsonUser;
import org.FuelPoints.entities.User;
import org.FuelPoints.services.TripRepository;
import org.FuelPoints.services.UserRepository;
import org.FuelPoints.services.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@CrossOrigin(origins = "*") //TODO: Lock down to deployed prod domain
@RestController
public class UserController {
    @Autowired
    UserRepository users;
    @Autowired
    VehicleRepository vehicles;
    @Autowired
    TripRepository trips;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    RootSerializer rootSerializer = new RootSerializer();
    UserSerializer userSerializer = new UserSerializer();

//    @RequestMapping(path = "/login", method = RequestMethod.POST)
//    public HashMap<String, Object> login(HttpServletResponse response, @RequestBody RootParser<JsonUser> jsonUser) throws Exception {
//
//        User user = users.findFirstByName(jsonUser.getData().getEntity().getName());
//        if (user == null) {
//            response.sendError(401, "Account does not exist.");
//        } else if (!user.verifyPassword(jsonUser.getData().getEntity().getPassword())) {
//            response.sendError(401, "Invalid credentials");
//        }
//        return rootSerializer.serializeOne(
//                "/users/" + user.getId(),
//                user,
//                userSerializer);
//    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public HashMap<String, Object> register(HttpServletResponse response, @RequestBody RootParser<JsonUser> parser) throws IOException {
        JsonUser jsonUser = parser.getData().getEntity();
        //TODO: check for null

        //TODO: try
        User user = users.findFirstByName(jsonUser.getName());
        if (user != null) {
            response.sendError(401, "Username is not available.");
        } else {
            user = new User(jsonUser.getName(), bCryptPasswordEncoder.encode(jsonUser.getPassword()));
            users.save(user);
            response.setStatus(201);
        }
        return rootSerializer.serializeOne(
                "/users/" + user.getId(),
                user,
                userSerializer);

    }

    @RequestMapping(path = "/users/current", method = RequestMethod.GET)
    public HashMap<String, Object> currentUser(){

        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByName(u.getName());

        return rootSerializer.serializeOne(
                "/users/" + user.getId(),
                user,
                userSerializer);
    }

}



