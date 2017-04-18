package org.FuelPoints.utilities.serializers;

import org.FuelPoints.utilities.HasId;
import org.FuelPoints.vessels.Year;

import java.util.HashMap;
import java.util.Map;

public class YearSerializer extends JsonDataSerializer {

    public String getType() {
        return "years";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        Year year = (Year) entity;

        result.put("value", year.getId());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}
