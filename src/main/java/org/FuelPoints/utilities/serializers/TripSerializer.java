package org.FuelPoints.utilities.serializers;

import org.FuelPoints.entities.Trip;
import org.FuelPoints.utilities.HasId;

import java.util.HashMap;
import java.util.Map;

public class TripSerializer extends JsonDataSerializer {

    public String getType() {
        return "users";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        Trip trip = (Trip) entity;

        result.put("trip-id", trip.getId());
        result.put("origin", trip.getOrigin());
        result.put("destination", trip.getDestination());
        result.put("total-distance", trip.getTotalDistance());
        result.put("total-duration", trip.getTotalDuration());
        result.put("fuel-burned", trip.getFuelBurned());
        result.put("co2-emissions", trip.getEmissions());

        //todo: adding user (not password), vehicle



        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}