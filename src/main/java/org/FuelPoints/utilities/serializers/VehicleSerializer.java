package org.FuelPoints.utilities.serializers;

import org.FuelPoints.entities.User;
import org.FuelPoints.entities.Vehicle;
import org.FuelPoints.utilities.HasId;

import java.util.HashMap;
import java.util.Map;

public class VehicleSerializer extends JsonDataSerializer {

    public String getType() {
        return "users";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        Vehicle vehicle = (Vehicle) entity;

        result.put("id", vehicle.getId());
        result.put("year", vehicle.getYear());
        result.put("make", vehicle.getMake());
        result.put("model", vehicle.getModel());
        result.put("option", vehicle.getOption());
        result.put("fuelEconomyId", vehicle.getFuelEconomyId());
        result.put("user", vehicle.getUser());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}