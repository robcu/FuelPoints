package org.FuelPoints.controllers;

import org.FuelPoints.entities.User;
import org.FuelPoints.services.UserRepository;
import org.FuelPoints.vessels.DataList;
import org.FuelPoints.utilities.serializers.DataListSerializer;
import org.FuelPoints.utilities.serializers.MenuItemsSerializer;
import org.FuelPoints.utilities.serializers.RootSerializer;
import org.FuelPoints.vessels.MenuItem;
import org.FuelPoints.vessels.MenuItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import org.FuelPoints.clients.FuelEconomy;

@CrossOrigin(origins = "*") //TODO: Lock down to deployed prod domain
@RestController
public class FuelEconomyController {
    @Autowired
    UserRepository users;

    RootSerializer rootSerializer = new RootSerializer();
    DataListSerializer dataListSerializer = new DataListSerializer();
    MenuItemsSerializer menuItemsSerializer = new MenuItemsSerializer();

    @RequestMapping(path = "/years", method = RequestMethod.GET)
    public ArrayList<String> years(HttpServletResponse response) throws IOException {

        DataList years = FuelEconomy.retrieveList("year");

        return years.getDataList();
    }

    @RequestMapping(path = "/makes", method = RequestMethod.GET)
    public ArrayList<String> makes(HttpServletResponse response,
                                   @RequestParam(value = "year") String year) throws IOException {

        DataList listOfMakes = FuelEconomy.retrieveList("make?year=" + year);

        return listOfMakes.getDataList();
    }

    @RequestMapping(path = "/models", method = RequestMethod.GET)
    public ArrayList<String> models(HttpServletResponse response,
                                    @RequestParam(value = "year") String year,
                                    @RequestParam (value = "make") String make) throws IOException {

        String urlExtension = "model?year=" + year + "&make=" + make;
        DataList listOfModels = FuelEconomy.retrieveList(urlExtension);

        return listOfModels.getDataList();
    }

    @RequestMapping(path = "/options", method = RequestMethod.GET)
    public ArrayList<MenuItem> options(HttpServletResponse response,
                                       @RequestParam(value = "year") String year,
                                       @RequestParam (value = "make") String make,
                                       @RequestParam (value = "model") String model) throws IOException {

        String urlExtension = "options?year="+ year +"&make="+ make +"&model="+ model;
        MenuItems listOfOptions = FuelEconomy.retrieveOptionsAndVehicleNumbers(urlExtension);

//        Authentication u = SecurityContextHolder.getContext().getAuthentication();
//        User user = users.findFirstByName(u.getName());
//
//        user.setOptionsCache(listOfOptions);
//        users.save(user);

        return listOfOptions.getMenuItems();
    }

}
