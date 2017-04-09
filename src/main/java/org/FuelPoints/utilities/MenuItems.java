package org.FuelPoints.utilities;

import org.FuelPoints.utilities.MenuItem;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="menuItems")
@XmlAccessorType(XmlAccessType.FIELD)
public class MenuItems {

    @XmlElement(name="menuItem")
    List<MenuItem> listOfMenuItems;

    public MenuItems(List<MenuItem> menuItems) {
        this.listOfMenuItems = menuItems;
    }

    public MenuItems() {

    }

    public List<MenuItem> getMenuItems() {
        return listOfMenuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.listOfMenuItems = menuItems;
    }
}
