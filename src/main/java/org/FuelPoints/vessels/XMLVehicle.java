package org.FuelPoints.vessels;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "vehicle")
@XmlAccessorType(XmlAccessType.NONE)
public class XMLVehicle {
    @XmlElement(name = "year")
    String year;
    @XmlElement(name = "make")
    String make;
    @XmlElement(name = "model")
    String model;
    @XmlElement(name = "city08U")
    Double cityMPG;
    @XmlElement(name = "highway08U")
    Double hwyMPG;
    @XmlElement(name = "comb08U")
    Double combMPG;
    @XmlElement(name = "city08")
    Double cityMPGb;
    @XmlElement(name = "highway08")
    Double hwyMPGb;
    @XmlElement(name = "comb08")
    Double combMPGb;


    public XMLVehicle() {
    }

    public XMLVehicle(String year, String make, String model, Double cityMPG, Double hwyMPG, Double combMPG) {
        this.year = year;
        this.make = make;
        this.model = model;
        if (this.cityMPG == 0) {
            this.cityMPG = cityMPGb;
        } else {
            this.cityMPG = cityMPG;
        }
        if (this.hwyMPG == 0) {
            this.hwyMPG = hwyMPGb;
        } else {
            this.hwyMPG = hwyMPG;
        }
        if (this.combMPG == 0) {
            this.combMPG = combMPGb;
        } else {
            this.combMPG = combMPG;
        }
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

    public Double getCityMPG() {
        return cityMPG;
    }

    public void setCityMPG(Double cityMPG) {
        this.cityMPG = cityMPG;
    }

    public Double getHwyMPG() {
        return hwyMPG;
    }

    public void setHwyMPG(Double hwyMPG) {
        this.hwyMPG = hwyMPG;
    }

    public Double getCombMPG() {
        return combMPG;
    }

    public void setCombMPG(Double combMPG) {
        this.combMPG = combMPG;
    }

    public Double getCityMPGb() {
        return cityMPGb;
    }

    public void setCityMPGb(Double cityMPGb) {
        this.cityMPGb = cityMPGb;
    }

    public Double getHwyMPGb() {
        return hwyMPGb;
    }

    public void setHwyMPGb(Double hwyMPGb) {
        this.hwyMPGb = hwyMPGb;
    }

    public Double getCombMPGb() {
        return combMPGb;
    }

    public void setCombMPGb(Double combMPGb) {
        this.combMPGb = combMPGb;
    }
}
