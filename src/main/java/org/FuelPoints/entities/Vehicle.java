package org.FuelPoints.entities;

import org.FuelPoints.utilities.HasId;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id;

    @Column
    String make;

    @Column
    String model;

    @Column
    Integer year;   //todo: make string?

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

    @Override
    public String getId() {
        return id;
    }
//    public void setId(String id){
//        this.id = id;
//    }

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
