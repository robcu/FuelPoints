package org.FuelPoints.utilities;

import org.FuelPoints.utilities.HasId;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

public class DataList implements HasId {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    ArrayList<String> dataList;

    public DataList() {
    }

    public void add(String string){
        this.dataList.add(string);
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