package org.FuelPoints.controllers;

import org.FuelPoints.utilities.DataList;
import org.FuelPoints.utilities.parsers.RootParser;
import org.FuelPoints.utilities.serializers.DataListSerializer;
import org.FuelPoints.utilities.serializers.RootSerializer;
import org.FuelPoints.utilities.serializers.VehicleSerializer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static org.FuelPoints.clients.FuelEconomy.retrieveList;

@RestController
public class FuelEconomyController {

    RootSerializer rootSerializer = new RootSerializer();
    DataListSerializer dataListSerializer = new DataListSerializer();

    @RequestMapping(path = "/account/vehicles/years", method = RequestMethod.GET)
    public HashMap<String, Object> years(HttpServletResponse response) throws IOException {

        DataList years = retrieveList("year");

        return rootSerializer.serializeMany(     //todo: should this be serializeMany ?
                "/years/" + "",
                years.getDataList(),
                dataListSerializer);
    }

    @RequestMapping(path = "/makes", method = RequestMethod.GET)
    public HashMap<String, Object> makes(HttpServletResponse response, @RequestParam(value = "filter[year]") String year) throws IOException {

        DataList dataList = retrieveList("make?year=" + year);

        return rootSerializer.serializeMany(
                "/makes?filter[year]=" + year,
                dataList.getDataList(),
                dataListSerializer);
    }

    @RequestMapping(path = "/account/vehicles/models", method = RequestMethod.POST)
    public HashMap<String, Object> models(HttpServletResponse response, @RequestBody RootParser<DataList> parser) throws IOException {

        String urlExtension = "make?year=" + parser.getData().getEntity().getDataList().get(0) + "&make=" + parser.getData().getEntity().getDataList().get(1);

        DataList dataList = retrieveList(urlExtension);


        return rootSerializer.serializeMany(     //todo: should this be serializeMany ?
                "/years/" + "",
                dataList.getDataList(),
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
