package org.FuelPoints.clients;

import org.FuelPoints.vessels.DataList;
import org.FuelPoints.vessels.MenuItem;
import org.FuelPoints.vessels.MenuItems;
import org.FuelPoints.vessels.XMLVehicle;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


public class FuelEconomy {

    public static String BASE_URL = "http://www.fueleconomy.gov/ws/rest/vehicle/menu/";

    public static DataList retrieveList(String urlExtension) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        MenuItems mi = restTemplate.getForObject(BASE_URL + urlExtension, MenuItems.class);

        DataList dataList = new DataList();
        for(MenuItem m : mi.getMenuItems()){
            dataList.add(m.getText());
        }
        return dataList;
    }

    public static MenuItems retrieveOptionsAndVehicleNumbers(String urlExtension){
        RestTemplate restTemplate = new RestTemplate();
        MenuItems mi = restTemplate.getForObject(BASE_URL + urlExtension, MenuItems.class);

        return mi;
    }

    public static XMLVehicle retrieveXMLVehicle(String id) {
        RestTemplate restTemplate = new RestTemplate();
        XMLVehicle xmlVehicle = restTemplate.getForObject("http://www.fueleconomy.gov/ws/rest/vehicle/" + id, XMLVehicle.class);

        return xmlVehicle;
    }


}
