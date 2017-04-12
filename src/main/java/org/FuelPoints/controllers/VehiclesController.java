package org.FuelPoints.controllers;

import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.services.VehicleRepository;
import org.FuelPoints.utilities.parsers.RootParser;
import org.FuelPoints.utilities.serializers.RootSerializer;
import org.FuelPoints.utilities.serializers.VehicleSerializer;
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

import static org.FuelPoints.clients.FuelEconomy.retrieveList;
import static org.FuelPoints.clients.FuelEconomy.retrieveOptionsAndVehicleNumber;
import static org.FuelPoints.clients.FuelEconomy.retrieveXMLVehicle;

@RestController
public class VehiclesController {
    @Autowired
    VehicleRepository vehicles;

    RootSerializer rootSerializer = new RootSerializer();
    VehicleSerializer vehicleSerializer = new VehicleSerializer();

    @RequestMapping(path = "/add_vehicle", method = RequestMethod.POST)
    public void addVehicle(HttpServletResponse response, @RequestBody RootParser<Vehicle> vehicle) throws IOException {

        //todo: how do i get "user" from front?

        XMLVehicle xmlVehicle = retrieveXMLVehicle(vehicle.getData().getId());

//      vehicles.save(new Vehicle(vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getUser()));
//      vehicles.save(vehicle);
        response.sendError(201, "Vehicle added.");

        //todo: return what? also save vehicle
    }

    @RequestMapping(path = "/delete_vehicle", method = RequestMethod.POST)
    public void deleteVehicle(HttpServletResponse response, @RequestBody RootParser<String> id) throws IOException {

        if (vehicles.exists(id.getData().getId())) {
            vehicles.delete(id.getData().getId());
            response.sendError(200, "Vehicle deleted");
        } else {
            response.sendError(400, "Error deleting vehicle.");
        }
        //todo: return what?
    }

    //todo: FOR BELOW
    //todo: parse
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
