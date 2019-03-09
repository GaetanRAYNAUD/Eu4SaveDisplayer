package com.graynaud.eu4savedisplayerbo.model.save.general;

public class CountryState {
    private Double prosperity;

    private String country;

    public Double getProsperity () {
        return prosperity;
    }

    public void setProsperity (Double prosperity) {
        this.prosperity = prosperity;
    }

    public String getCountry () {
        return country;
    }

    public void setCountry (String country) {
        this.country = country;
    }

    @Override
    public String toString () {
        return "CountryState{" +
                "prosperity=" + prosperity +
                ", country='" + country + '\'' +
                '}';
    }
}
