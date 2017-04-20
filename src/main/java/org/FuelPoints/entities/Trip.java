package org.FuelPoints.entities;

import org.FuelPoints.utilities.HasId;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trips")
public class Trip implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;
    @Column
    String origin;
    @Column
    String destination;
    @Column
    Double totalDistance;
    @Column
    Double totalDuration;
    @Column
    Double fuelBurned;
    @Column
    Double emissions;
    @Column
    Float fuelGallonPrice;
    @Column
    Double totalFuelPrice;

    @ManyToOne
    User user;

    @ManyToOne
    Vehicle vehicle;

    public Trip(Trip trip) {
        this.origin = trip.getOrigin();
        this.destination = trip.getDestination();
        this.totalDistance = trip.getTotalDistance();
        this.totalDuration = trip.getTotalDuration();
    }

    public Trip() {
    }

    public Trip(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Trip(String origin, String destination, User user, Vehicle vehicle) {
        this.origin = origin;
        this.destination = destination;
        this.user = user;
        this.vehicle = vehicle;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public Float getFuelGallonPrice() {
        return fuelGallonPrice;
    }

    public void setFuelGallonPrice(Float fuelPrice) {
        this.fuelGallonPrice = fuelPrice;
        setFuelBurned(((totalDistance * 3.2808) / 5280) / this.vehicle.getCombMPG());    //todo: allow for cityMPG and hwyMPG
        setTotalFuelPrice(fuelPrice * this.fuelBurned);
        setEmissions(this.fuelBurned * 8887);
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

    public Double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Double totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Double getTotalFuelPrice() {
        return totalFuelPrice;
    }

    public void setTotalFuelPrice(Double totalFuelPrice) {
        this.totalFuelPrice = totalFuelPrice;
    }

    public Double getMiles() {
        return (totalDistance * 3.2808) / 5280;
    }

    public String getTime() {
        Double doubleHours = (this.totalDuration / 3600);
        Integer hours = doubleHours.intValue();
        Double doubleMinutes = doubleHours - hours;
        Integer minutes = doubleMinutes.intValue();

        return hours + "h " + minutes + "m";
    }
}
