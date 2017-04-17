package org.FuelPoints.vessels;

import org.hibernate.annotations.GenericGenerator;
import org.FuelPoints.utilities.HasId;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class DataList implements HasId {

    String id;

    ArrayList<String> dataList = new ArrayList<>();

    public DataList() {
    }

    public void add(String string){
        dataList.add(string);
    }

    public DataList(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    public ArrayList<String> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
}