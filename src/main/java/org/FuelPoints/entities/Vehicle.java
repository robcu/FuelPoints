package org.FuelPoints.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.FuelPoints.utilities.HasId;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    @Column
    String make;

    @Column
    String model;

    @Column
    String year;

    @Column
    String option;

    @Column
    @JsonProperty("fuel-economy-id")
    String fuelEconomyId;

    @Column
    @JsonProperty("city-mpg")
    Double cityMPG;

    @Column
    @JsonProperty("hwy-mpg")
    Double hwyMPG;

    @Column
    @JsonProperty("comb-mpg")
    Double combMPG;

    @ManyToOne
    User user;

    public Vehicle() {
    }

    public Vehicle(String make, String model, String year, String option, String fuelEconomyId, Double cityMPG, Double hwyMPG, Double combMPG, User user) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.option = option;
        this.fuelEconomyId = fuelEconomyId;
        this.cityMPG = cityMPG;
        this.hwyMPG = hwyMPG;
        this.combMPG = combMPG;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getFuelEconomyId() {
        return fuelEconomyId;
    }

    public void setFuelEconomyId(String fuelEconomyId) {
        this.fuelEconomyId = fuelEconomyId;
    }
}
