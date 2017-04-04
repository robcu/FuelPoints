package org.FuelPoints.entities;

import java.util.List;

public class User {

    String name;
    String password;
    List<Vehicle> vehicles;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
