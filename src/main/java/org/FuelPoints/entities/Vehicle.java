package org.FuelPoints.entities;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue
    Integer id;

    @Column
    String make;

    @Column
    String model;

    @Column
    Integer year;

    @ManyToOne
    User user;

    public Vehicle() {
    }

    public Vehicle(String make, String model, Integer year, User user) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.user = user;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
