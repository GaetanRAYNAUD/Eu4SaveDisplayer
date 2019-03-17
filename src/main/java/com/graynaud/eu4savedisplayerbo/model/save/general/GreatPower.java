package com.graynaud.eu4savedisplayerbo.model.save.general;

import java.io.Serializable;

public class GreatPower implements Serializable {
    private String country;

    private Double value;

    public String getCountry () {
        return country;
    }

    public void setCountry (String country) {
        this.country = country;
    }

    public Double getValue () {
        return value;
    }

    public void setValue (Double value) {
        this.value = value;
    }

    @Override
    public String toString () {
        return "GreatPower{" +
                "country='" + country + '\'' +
                ", value=" + value +
                '}';
    }
}
