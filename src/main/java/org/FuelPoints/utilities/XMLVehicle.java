package org.FuelPoints.utilities;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "vehicle")
@XmlAccessorType(XmlAccessType.NONE)
public class XMLVehicle {
    @XmlElement(name = "make")
    String make;
    @XmlElement(name = "model")
    String model;
    @XmlElement(name = "year")
    String year;

    public XMLVehicle(String make, String model, String year) {

        this.make = make;
        this.model = model;
        this.year = year;
    }

    public XMLVehicle() {

    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
