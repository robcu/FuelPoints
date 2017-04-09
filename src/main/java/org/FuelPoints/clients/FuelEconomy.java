package org.FuelPoints.clients;

import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.vessels.MenuItem;
import org.FuelPoints.vessels.MenuItems;
import org.FuelPoints.vessels.XMLVehicle;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

public class FuelEconomy {

    public static ArrayList<String> getListOfYears() throws IOException {
//
        RestTemplate restTemplate = new RestTemplate();
        MenuItems mi = restTemplate.getForObject("http://www.fueleconomy.gov/ws/rest/vehicle/menu/year", MenuItems.class);
        ArrayList<String> listOfYears = new ArrayList<>();
        for(MenuItem m : mi.getMenuItems()){
            listOfYears.add(m.getValue());
        }
        return listOfYears;
    }

    //todo: Choose appropriate object type to return for each of these methods.
    //todo: (cont..) Do more processing here so less has to be done in the route.

    public static XMLVehicle getXMLVehicle() {

        RestTemplate restTemplate = new RestTemplate();
        XMLVehicle xmlVehicle = restTemplate.getForObject("http://www.fueleconomy.gov/ws/rest/vehicle/31873", XMLVehicle.class);
        System.out.println(xmlVehicle.getMake() + ", " + xmlVehicle.getModel() + ", " + xmlVehicle.getYear());

        return xmlVehicle;
    }

    public static String getOptions(){
        RestTemplate restTemplate = new RestTemplate();
        MenuItems list  = restTemplate.getForObject("http://www.fueleconomy.gov/ws/rest/vehicle/menu/options?year=2012&make=Honda&model=Fit", MenuItems.class);

        for (int i = 0; i < list.getMenuItems().size(); i++){
            System.out.println(list.getMenuItems().get(i).getText());
        }

        return "";
    }
}
