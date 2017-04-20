package org.FuelPoints.entities;

import org.FuelPoints.utilities.HasId;
import org.FuelPoints.vessels.MenuItems;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    MenuItems optionsCache;

    ArrayList<Trip> tripCache;


    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuItems getOptionsCache() {
        return optionsCache;
    }

    public void setOptionsCache(MenuItems optionsCache) {
        this.optionsCache = optionsCache;
    }

    public ArrayList<Trip> getTripCache() {
        return tripCache;
    }

    public void setTripCache(ArrayList<Trip> tripCache) {
        this.tripCache = tripCache;
    }
}
