package org.FuelPoints.utilities;

import org.FuelPoints.utilities.MenuItem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="menuItems")
@XmlAccessorType(XmlAccessType.NONE)
public class MenuItems {
    @XmlAttribute(name="menuItem")
    List<MenuItem> listOfMenuItems;

    public MenuItems(ArrayList<MenuItem> menuItems) {
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
