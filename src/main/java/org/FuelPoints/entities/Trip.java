package org.FuelPoints.entities;

import javax.persistence.*;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue
    String id;

    @Column
    String startLoc;

    @Column
    String endLoc;

    @Column
    Double totalDistance;

    @Column
    Double fuelBurned;

    @Column
    Double emissions;

    @Column
    Float fuelPrice;

    @ManyToOne
    User user;

    @ManyToOne
    Vehicle vehicle;


    public Trip() {
    }

    public Trip(String startLoc, String endLoc, User user, Vehicle vehicle) {
        this.startLoc = startLoc;
        this.endLoc = endLoc;
        this.user = user;
        this.vehicle = vehicle;
    }

    public String getId() {
        return id;
    }

    public String getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }

    public String getEndLoc() {
        return endLoc;
    }

    public void setEndLoc(String endLoc) {
        this.endLoc = endLoc;
    }

    public Double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Double getFuelBurned() {
        return fuelBurned;
    }

    public void setFuelBurned(Double fuelBurned) {
        this.fuelBurned = fuelBurned;
    }

    public Double getEmissions() {
        return emissions;
    }

    public void setEmissions(Double emissions) {
        this.emissions = emissions;
    }

    public Float getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(Float fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
