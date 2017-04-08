package org.FuelPoints.clients;

import org.FuelPoints.utilities.MenuItems;
import org.FuelPoints.utilities.TempVehicle;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class FuelEconomy {

    public static String getYears() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        TempVehicle mi  = restTemplate.getForObject("http://www.fueleconomy.gov/ws/rest/vehicle/31873", TempVehicle.class);
        System.out.println(mi.getMake() + " " + mi.getModel() + " " + mi.getYear());


        //todo: i sent json at my routes to test parsing. now i need to send xml at a new route to test this?


        //System.out.println(mi.getMenuItems().get(0).getValue());

//        ObjectMapper mapper = new ObjectMapper();
//        Map<String,Object> map = mapper.readValue(s, Map.class);
//        Object o = map.get();
//        o.toString();
//        System.out.println(o);
//        JsonParser p = new JsonParser();
//        MenuItem years = p.parse(s, MenuItem.class);
//        System.out.println(years.getItems().get(0).containsKey("text"));

        return "";
    }
}
