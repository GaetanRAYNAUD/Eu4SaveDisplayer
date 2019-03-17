package com.graynaud.eu4savedisplayerbo.model.save.general;

import java.io.Serializable;
import java.util.Date;

public class OldEmperor implements Comparable<OldEmperor>, Serializable {
    private String country;

    private Date date;

    public String getCountry () {
        return country;
    }

    public void setCountry (String country) {
        this.country = country;
    }

    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    @Override
    public int compareTo (OldEmperor other) {
        return date.compareTo(other.date);
    }

    @Override
    public String toString () {
        return "OldEmperor{" +
                "country='" + country + '\'' +
                ", date=" + date +
                '}';
    }
}
