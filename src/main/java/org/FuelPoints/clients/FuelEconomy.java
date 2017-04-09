package org.FuelPoints.clients;

import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.vessels.MenuItem;
import org.FuelPoints.vessels.MenuItems;
import org.FuelPoints.vessels.XMLVehicle;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

public class FuelEconomy {

    public static ArrayList<String> retrieveList(String url) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        MenuItems mi = restTemplate.getForObject(url, MenuItems.class);
        ArrayList<String> listOfValues = new ArrayList<>();
        for(MenuItem m : mi.getMenuItems()){
            listOfValues.add(m.getValue());
        }
        return listOfValues;
    }
    //todo: Choose appropriate object type to return for each of these methods.
    //todo: (cont..) Do more processing here so less has to be done in the route.


    public static XMLVehicle retrieveXMLVehicle() {

        RestTemplate restTemplate = new RestTemplate();
        XMLVehicle xmlVehicle = restTemplate.getForObject("http://www.fueleconomy.gov/ws/rest/vehicle/31873", XMLVehicle.class);
        System.out.println(xmlVehicle.getMake() + ", " + xmlVehicle.getModel() + ", " + xmlVehicle.getYear());

        //todo: convert to regular vehicle? would need user info
        return xmlVehicle;
    }
}
