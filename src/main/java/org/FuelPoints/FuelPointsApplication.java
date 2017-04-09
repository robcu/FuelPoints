package org.FuelPoints;

import org.FuelPoints.clients.FuelEconomy;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class FuelPointsApplication {

	public static void main(String[] args) throws IOException {
		FuelEconomy.getListOfYears();
		//SpringApplication.run(FuelPointsApplication.class, args);

	}
}
