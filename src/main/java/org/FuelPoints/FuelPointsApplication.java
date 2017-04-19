package org.FuelPoints;

import org.FuelPoints.clients.FuelEconomy;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.vessels.XMLVehicle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;

import static org.FuelPoints.clients.GoogleMaps.retrieveDirections;

@SpringBootApplication
public class FuelPointsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(FuelPointsApplication.class, args);

		//retrieveDirections("Nashville,TN", "Los+Angeles,CA");
//
//        XMLVehicle one = FuelEconomy.retrieveXMLVehicle("33261"); //4runner
//        XMLVehicle two = FuelEconomy.retrieveXMLVehicle("33324");//prius   //todo: I need to get option string for each of these
//        XMLVehicle three = FuelEconomy.retrieveXMLVehicle("35485");//accord  //todo: and put all 3 into a list
//
//        Vehicle vehicleOne = new Vehicle(one.getYear(), one.getMake(), one.getModel(),
//                optionText, "33261", one.getCityMPG(), one.getHwyMPG(), one.getCombMPG());
//        Vehicle vehicleTwo = new Vehicle(two.getYear(), two.getMake(), two.getModel(),
//                optionText, "33324", two.getCityMPG(), two.getHwyMPG(), two.getCombMPG());
//        Vehicle vehicleThree = new Vehicle(three.getYear(), three.getMake(), three.getModel(),
//                optionText, "35485", three.getCityMPG(), three.getHwyMPG(), three.getCombMPG());


    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*");           //todo: update to limit origin domain when deployed
            }
        };
    }
}
