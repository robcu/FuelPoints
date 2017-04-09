package org.FuelPoints.controllers;

import org.FuelPoints.vessels.JsonUser;
import org.FuelPoints.entities.User;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.services.TripRepository;
import org.FuelPoints.services.UserRepository;
import org.FuelPoints.services.VehicleRepository;
import org.FuelPoints.utilities.PasswordStorage;
import org.FuelPoints.vessels.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;

import static org.FuelPoints.clients.FuelEconomy.retrieveList;
import static org.FuelPoints.clients.FuelEconomy.retrieveOptionsAndVehicleNumber;


@RestController
public class FuelPointsController {
    @Autowired
    UserRepository users;
    @Autowired
    VehicleRepository vehicles;
    @Autowired
    TripRepository trips;


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(HttpServletResponse response, @RequestBody @Valid JsonUser jsonUser) throws Exception {
//        JsonParser p = new JsonParser();
//        JsonUser jsonUser = p.parse(body, JsonUser.class);

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
    public void register(HttpServletResponse response, @RequestBody @Valid JsonUser jsonUser) throws IOException, PasswordStorage.CannotPerformOperationException {
//        JsonParser p = new JsonParser();
//        JsonUser jsonUser = p.parse(body, JsonUser.class);

        User user = users.findFirstByName(jsonUser.getName());
        if (user != null) {
            response.sendError(401, "Username is not available.");
        } else {
            users.save(new User(jsonUser.getName(), jsonUser.getPassword()));
            response.sendError(201, "User successfully created.");
        }
    }

    @RequestMapping(path = "/add_vehicle", method = RequestMethod.POST)
    public void addVehicle(HttpServletResponse response, @RequestBody @Valid Vehicle vehicle) throws IOException {
//        JsonParser p = new JsonParser();
//        Vehicle vehicle = p.parse(body, Vehicle.class);
//        vehicles.save(new Vehicle(vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getUser()));

        vehicles.save(vehicle);
        response.sendError(201, "Vehicle added.");
    }

    @RequestMapping(path = "/delete_vehicle", method = RequestMethod.POST)
    public void deleteVehicle(HttpServletResponse response, @RequestBody @Valid Integer id) throws IOException {
//        JsonParser p = new JsonParser();
//        Integer id = p.parse(body, Integer.class);

        if (vehicles.exists(id)) {
            vehicles.delete(id);
            response.sendError(200, "Vehicle deleted");
        } else {
            response.sendError(400, "Error deleting vehicle.");
        }
    }

    //todo: FOR BELOW
    //todo: convert returned ArrayList<String> to JSON?
    //todo: take parameters from @RequestBody to insert into url string

    @RequestMapping(path = "/account/vehicles/years", method = RequestMethod.POST)
    public ArrayList<String> years(HttpServletResponse response) throws IOException {

        return retrieveList("http://www.fueleconomy.gov/ws/rest/vehicle/menu/year");
    }

    @RequestMapping(path = "/account/vehicles/makes", method = RequestMethod.POST)
    public ArrayList<String> makes(HttpServletResponse response) throws IOException {

        return retrieveList("http://www.fueleconomy.gov/ws/rest/vehicle/menu/make?year=2012");
    }

    @RequestMapping(path = "/account/vehicles/models", method = RequestMethod.POST)
    public ArrayList<String> models(HttpServletResponse response) throws IOException {

        return retrieveList("http://www.fueleconomy.gov/ws/rest/vehicle/menu/model?year=2012&make=Honda");
    }

    @RequestMapping(path = "/account/vehicles/options", method = RequestMethod.POST)
    public ArrayList<MenuItem> options(HttpServletResponse response) throws IOException {
    //todo: do i need to convert arraylist<menuItem> into a hashmap? can I do that at the menuitem or menuitems class level?

        return retrieveOptionsAndVehicleNumber("http://www.fueleconomy.gov/ws/rest/vehicle/menu/options?year=2012&make=Honda&model=Fit");
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



