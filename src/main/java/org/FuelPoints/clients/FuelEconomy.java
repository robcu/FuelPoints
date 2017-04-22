package org.FuelPoints.clients;

import org.FuelPoints.FuelPointsApplication;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.vessels.DataList;
import org.FuelPoints.vessels.MenuItem;
import org.FuelPoints.vessels.MenuItems;
import org.FuelPoints.vessels.XMLVehicle;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;


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

    public static ArrayList<Vehicle> hardCodeThreeVehicles(){

        String touaregFEID = "32738";
        String touaregOption = "Auto (S8), 6 cyl, 3.6 L";
        String priusFEID = "33324";
        String priusOption = "Auto (variable gear ratios), 4 cyl, 1.8 L";
        String accordFEID = "33173";
        String accordOption = "Auto (S6), 6 cyl, 3.5 L";

        XMLVehicle one = FuelEconomy.retrieveXMLVehicle(touaregFEID);
        XMLVehicle two = FuelEconomy.retrieveXMLVehicle(priusFEID);
        XMLVehicle three = FuelEconomy.retrieveXMLVehicle(accordFEID);

        Vehicle vehicleOne = new Vehicle(one.getYear(), one.getMake(), one.getModel(),
                touaregOption, touaregFEID, one.getCityMPG(), one.getHwyMPG(), one.getCombMPG());
        Vehicle vehicleTwo = new Vehicle(two.getYear(), two.getMake(), two.getModel(),
                priusOption, priusFEID, two.getCityMPG(), two.getHwyMPG(), two.getCombMPG());
        Vehicle vehicleThree = new Vehicle(three.getYear(), three.getMake(), three.getModel(),
                accordOption, accordFEID, three.getCityMPG(), three.getHwyMPG(), three.getCombMPG());

        ArrayList<Vehicle> vehiclesList = new ArrayList<>();
        vehiclesList.add(vehicleOne);
        vehiclesList.add(vehicleTwo);
        vehiclesList.add(vehicleThree);

        return vehiclesList;
    }
}
