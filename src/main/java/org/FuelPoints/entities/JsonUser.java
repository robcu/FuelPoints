package org.FuelPoints.entities;

public class JsonUser {
    String name;
    private String password;

    public JsonUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public JsonUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
