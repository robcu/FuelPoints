package org.FuelPoints;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import static org.FuelPoints.clients.GoogleMaps.retrieveDirections;

@SpringBootApplication
public class FuelPointsApplication {

	public static void main(String[] args) throws IOException {
//		SpringApplication.run(FuelPointsApplication.class, args);

		retrieveDirections();
	}
}
