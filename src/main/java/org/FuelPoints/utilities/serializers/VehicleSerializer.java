package org.FuelPoints.utilities.serializers;

import org.FuelPoints.entities.User;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.utilities.HasId;

import java.util.HashMap;
import java.util.Map;

public class VehicleSerializer extends JsonDataSerializer {

    public String getType() {
        return "vehicles";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        Vehicle vehicle = (Vehicle) entity;

        result.put("id", vehicle.getId());
        result.put("year", vehicle.getYear());
        result.put("make", vehicle.getMake());
        result.put("model", vehicle.getModel());
        result.put("option", vehicle.getOption());
        result.put("fuel-economy-id", vehicle.getFuelEconomyId());
        result.put("city-mpg", vehicle.getCityMPG());
        result.put("hwy-mpg", vehicle.getHwyMPG());
        result.put("comb-mpg", vehicle.getCombMPG());
        result.put("user-id", vehicle.getUser().getId());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}