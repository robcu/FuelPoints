package org.FuelPoints.controllers;

import org.FuelPoints.entities.DataList;
import org.FuelPoints.utilities.serializers.DataListSerializer;
import org.FuelPoints.utilities.serializers.RootSerializer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static org.FuelPoints.clients.FuelEconomy.retrieveList;

@CrossOrigin(origins = "*") //TODO: Lock down to deployed prod domain
@RestController
public class FuelEconomyController {

    RootSerializer rootSerializer = new RootSerializer();
    DataListSerializer dataListSerializer = new DataListSerializer();

    @RequestMapping(path = "/years", method = RequestMethod.GET)
    public HashMap<String, Object> years(HttpServletResponse response) throws IOException {

        DataList years = retrieveList("year");

        return rootSerializer.serializeOne(
                "/years/" + years.getId(),
                years,
                dataListSerializer);
    }

    @RequestMapping(path = "/makes", method = RequestMethod.GET)
    public HashMap<String, Object> makes(HttpServletResponse response, @RequestParam(value = "year") String year) throws IOException {

        DataList listOfMakes = retrieveList("make?year=" + year);

        return rootSerializer.serializeOne(
                "/make?year=" + year,
                listOfMakes,
                dataListSerializer);
    }

    @RequestMapping(path = "/models", method = RequestMethod.GET)
    public HashMap<String, Object> models(HttpServletResponse response, @RequestParam(value = "year") String year, @RequestParam (value = "make") String make) throws IOException {

        String urlExtension = "model?year=" + year + "&make=" + make;
        DataList listOfModels = retrieveList(urlExtension);

        return rootSerializer.serializeOne(
                "/model?year"+ year +"&make="+ make +"/" + "",
                listOfModels,
                dataListSerializer);
    }
//
//    @RequestMapping(path = "/account/vehicles/options", method = RequestMethod.GET)
//    public HashMap<String, Object> options(HttpServletResponse response) throws IOException {
//
//        retrieveOptionsAndVehicleNumbers("http://www.fueleconomy.gov/ws/rest/vehicle/menu/options?year=2012&make=Honda&model=Fit");
//
//        return
//    }

}
