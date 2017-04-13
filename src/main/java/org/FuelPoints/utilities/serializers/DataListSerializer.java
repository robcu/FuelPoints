package org.FuelPoints.utilities.serializers;

import org.FuelPoints.utilities.HasId;
import org.FuelPoints.entities.DataList;

import java.util.HashMap;
import java.util.Map;

public class DataListSerializer extends JsonDataSerializer {

    public String getType() {
        return "dataList";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        DataList dataList = (DataList) entity;

        result.put("dataList", dataList.getDataList());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}