package org.FuelPoints.vessels;

import org.FuelPoints.vessels.MenuItem;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name="menuItems")
@XmlAccessorType(XmlAccessType.FIELD)
public class MenuItems {

    @XmlElement(name="menuItem")
    ArrayList<MenuItem> listOfMenuItems;

    public MenuItems(ArrayList<MenuItem> menuItems) {
        this.listOfMenuItems = menuItems;
    }

    public MenuItems() {

    }

    public ArrayList<MenuItem> getMenuItems() {
        return listOfMenuItems;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.listOfMenuItems = menuItems;
    }
}
