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
import java.util.ArrayList;

import static org.FuelPoints.clients.GoogleMaps.retrieveDirections;

@SpringBootApplication
public class FuelPointsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(FuelPointsApplication.class, args);

		//retrieveDirections("Nashville,TN", "Los+Angeles,CA");


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
