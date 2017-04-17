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
    String year;   //todo: make string?

    @Column
    String option;

    @Column
    @JsonProperty("fuel-ecomomy-id")
    String fuelEconomyId;

    @ManyToOne
    User user;

    public Vehicle() {
    }

    public Vehicle(String year, String make, String model, String fuelEconomyId, String option, User user) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.option = option;
        this.fuelEconomyId = fuelEconomyId;
        this.user = user;
    }

    @Override
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
