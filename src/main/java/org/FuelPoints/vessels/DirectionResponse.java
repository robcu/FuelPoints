package org.FuelPoints.vessels;

import org.FuelPoints.entities.Trip;
import org.FuelPoints.utilities.HasId;

import java.util.ArrayList;

public class DirectionResponse implements HasId{

    String id = "1";
    Object json;

    ArrayList<Trip> tripsWithVehicleOne = new ArrayList<>();
    ArrayList<Trip> tripsWithVehicleTwo = new ArrayList<>();
    ArrayList<Trip> tripsWithVehicleThree = new ArrayList<>();

    public DirectionResponse() {
    }

    public DirectionResponse(String json, ArrayList<Trip> tripsWithVehicleOne, ArrayList<Trip> tripsWithVehicleTwo, ArrayList<Trip> tripsWithVehicleThree) {
        this.json = json;
        this.tripsWithVehicleOne = tripsWithVehicleOne;
        this.tripsWithVehicleTwo = tripsWithVehicleTwo;
        this.tripsWithVehicleThree = tripsWithVehicleThree;
    }

    public void addToOne (Trip trip){
        this.tripsWithVehicleOne.add(trip);
    }
    public void addToTwo (Trip trip){
        this.tripsWithVehicleTwo.add(trip);
    }
    public void addToThree (Trip trip){
        this.tripsWithVehicleThree.add(trip);
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Trip> getTripsWithVehicleOne() {
        return tripsWithVehicleOne;
    }

    public void setTripsWithVehicleOne(ArrayList<Trip> tripsWithVehicleOne) {
        this.tripsWithVehicleOne = tripsWithVehicleOne;
    }

    public ArrayList<Trip> getTripsWithVehicleTwo() {
        return tripsWithVehicleTwo;
    }

    public void setTripsWithVehicleTwo(ArrayList<Trip> tripsWithVehicleTwo) {
        this.tripsWithVehicleTwo = tripsWithVehicleTwo;
    }

    public ArrayList<Trip> getTripsWithVehicleThree() {
        return tripsWithVehicleThree;
    }

    public void setTripsWithVehicleThree(ArrayList<Trip> tripsWithVehicleThree) {
        this.tripsWithVehicleThree = tripsWithVehicleThree;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }
}
