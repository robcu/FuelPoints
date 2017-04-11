package org.FuelPoints.utilities.serializers;

import org.FuelPoints.entities.User;
import org.FuelPoints.utilities.HasId;

import java.util.HashMap;
import java.util.Map;

public class UserSerializer extends JsonDataSerializer {

    public String getType() {
        return "users";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) entity;

        result.put("name", user.getName());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}