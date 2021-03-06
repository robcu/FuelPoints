package org.FuelPoints.vessels;

import org.FuelPoints.utilities.HasId;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "menuItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class MenuItem implements HasId{
    String id;
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

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
}


