package org.FuelPoints.utilities.serializers;

import org.FuelPoints.utilities.HasId;
import org.FuelPoints.vessels.DirectionResponse;

import java.util.HashMap;
import java.util.Map;

public class DirectionResponseSerializer extends JsonDataSerializer {

    public String getType() {
        return "menuItems";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
         DirectionResponse directionResponse = (DirectionResponse) entity;

        result.put("id", directionResponse.getId());
        result.put("json", directionResponse.getJson());
        result.put("trips-with-vehicle-one", directionResponse.getTripsWithVehicleOne());
        result.put("trips-with-vehicle-two", directionResponse.getTripsWithVehicleTwo());
        result.put("trips-with-vehicle-three", directionResponse.getTripsWithVehicleThree());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}