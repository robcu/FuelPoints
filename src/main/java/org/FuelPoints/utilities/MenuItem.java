package org.FuelPoints.utilities;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;

@XmlRootElement(name = "menuItem")
@XmlAccessorType(XmlAccessType.NONE)
public class MenuItem {
    @XmlElement(name="text")
    String text;
    @XmlElement(name="value")
    String value;

    public MenuItem(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MenuItem() {

    }
}


