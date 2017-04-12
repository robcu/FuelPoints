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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static org.FuelPoints.clients.FuelEconomy.retrieveList;
import static org.FuelPoints.clients.FuelEconomy.retrieveOptionsAndVehicleNumbers;
import static org.FuelPoints.clients.FuelEconomy.retrieveXMLVehicle;

@RestController
public class VehiclesController {
    @Autowired
    VehicleRepository vehicles;

    RootSerializer rootSerializer = new RootSerializer();
    VehicleSerializer vehicleSerializer = new VehicleSerializer();
    DataListSerializer dataListSerializer = new DataListSerializer();

    @RequestMapping(path = "/add_vehicle", method = RequestMethod.POST)
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

    @RequestMapping(path = "/delete_vehicle", method = RequestMethod.POST)
    public HashMap<String, Object> deleteVehicle(HttpServletResponse response, @RequestBody RootParser<String> id) throws IOException {
        Vehicle vehicle = new Vehicle();
        if (vehicles.exists(id.getData().getId())) {
            vehicle = vehicles.findOne(id.getData().getId());
            vehicles.delete(id.getData().getId());
            response.sendError(200, "Vehicle deleted");
        } else {
            response.sendError(400, "Error deleting vehicle.");
        }
        return rootSerializer.serializeOne(
                "/vehicles/" + vehicle.getId(),
                vehicle,
                vehicleSerializer);
    }


    @RequestMapping(path = "/account/vehicles/years", method = RequestMethod.POST)
    public HashMap<String, Object> years(HttpServletResponse response, @RequestBody RootParser<String> temp) throws IOException {

        DataList years = retrieveList("year");

        return rootSerializer.serializeOne(     //todo: should this be serializeMany ?
                "/years/" + "",
                years,
                dataListSerializer);
    }

    @RequestMapping(path = "/account/vehicles/makes", method = RequestMethod.POST)
    public HashMap<String, Object> makes(HttpServletResponse response, @RequestBody RootParser<String> year) throws IOException {

        String urlExtension = "make?year=" + year.getData().getEntity();

        DataList dataList = retrieveList(urlExtension);

        return rootSerializer.serializeOne(     //todo: should this be serializeMany ?
                "/years/" + "",
                dataList,
                dataListSerializer);
    }

    @RequestMapping(path = "/account/vehicles/models", method = RequestMethod.POST)
    public HashMap<String, Object> models(HttpServletResponse response, @RequestBody RootParser<DataList> parser) throws IOException {

        String urlExtension = "make?year=" + parser.getData().getEntity().getDataList().get(0) + "&make=" + parser.getData().getEntity().getDataList().get(1);

        DataList dataList = retrieveList(urlExtension);


        return rootSerializer.serializeOne(     //todo: should this be serializeMany ?
                "/years/" + "",
                dataList,
                dataListSerializer);
    }

//    @RequestMapping(path = "/account/vehicles/options", method = RequestMethod.POST)
//    public HashMap<String, Object> options(HttpServletResponse response) throws IOException {
//
//        retrieveOptionsAndVehicleNumbers("http://www.fueleconomy.gov/ws/rest/vehicle/menu/options?year=2012&make=Honda&model=Fit");
//
//        return
//    }
}
