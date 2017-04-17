package org.FuelPoints.utilities.serializers;

import org.FuelPoints.utilities.HasId;
import org.FuelPoints.vessels.MenuItems;

import java.util.HashMap;
import java.util.Map;

public class MenuItemsSerializer extends JsonDataSerializer {

    public String getType() {
        return "menuItems";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        MenuItems menuItems = (MenuItems) entity;

        result.put("menuItems", menuItems);

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}