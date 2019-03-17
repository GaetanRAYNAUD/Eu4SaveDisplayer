package com.graynaud.eu4savedisplayerbo.model.save.general;

import java.io.Serializable;
import java.util.List;

public class MapAreaData implements Serializable {
    private String key;

    private List<CountryState> countryStates;

    public String getKey () {
        return key;
    }

    public void setKey (String key) {
        this.key = key;
    }

    public List<CountryState> getCountryStates () {
        return countryStates;
    }

    public void setCountryStates (List<CountryState> countryStates) {
        this.countryStates = countryStates;
    }

    @Override
    public String toString () {
        return "MapAreaData{" +
                "key='" + key + '\'' +
                ", countryStates=" + countryStates +
                '}';
    }
}
