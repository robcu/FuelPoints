package org.FuelPoints.entities;

import javax.persistence.*;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue
    Integer id;

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
}
