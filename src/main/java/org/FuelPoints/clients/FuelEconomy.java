package org.FuelPoints.clients;

import org.FuelPoints.utilities.MenuItems;
import org.FuelPoints.utilities.XMLVehicle;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class FuelEconomy {

    public static String getYears() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        XMLVehicle mi  = restTemplate.getForObject("http://www.fueleconomy.gov/ws/rest/vehicle/31873", XMLVehicle.class);
        System.out.println(mi.getMake() + ", " + mi.getModel() + ", " + mi.getYear());

        MenuItems list  = restTemplate.getForObject("http://www.fueleconomy.gov/ws/rest/vehicle/menu/year", MenuItems.class);
        for (int i = 0; i < list.getMenuItems().size(); i++){
            System.out.print(list.getMenuItems().get(i).getValue() + ", ");
        }

        return "";
    }
}
