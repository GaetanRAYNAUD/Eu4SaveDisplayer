package com.graynaud.eu4savedisplayerbo.model.save;

import com.graynaud.eu4savedisplayerbo.model.save.country.Country;
import com.graynaud.eu4savedisplayerbo.model.save.general.*;
import com.graynaud.eu4savedisplayerbo.model.save.province.Province;
import com.graynaud.eu4savedisplayerbo.model.save.war.War;

import java.util.Date;
import java.util.List;
import java.util.SortedSet;

public class Eu4Save {

    private Date date;

    private SaveGameVersion saveGameVersion;

    private List<DLC> dlcEnabled;

    private Boolean multiPlayer;

    private Date startDate;

    private List<MapAreaData> mapAreaData;

    private List<Institution> institutions; //Arraylist

    private List<String> productionsLeader; //Arraylist

    private List<GreatPower> greatPowers; //arraylist

    private Empire empire;

    private Empire celestialEmpire;

    private List<Province> provinces;

    private List<Country> countries;

    private SortedSet<War> wars;

    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    public SaveGameVersion getSaveGameVersion () {
        return saveGameVersion;
    }

    public void setSaveGameVersion (SaveGameVersion saveGameVersion) {
        this.saveGameVersion = saveGameVersion;
    }

    public List<DLC> getDlcEnabled () {
        return dlcEnabled;
    }

    public void setDlcEnabled (List<DLC> dlcEnabled) {
        this.dlcEnabled = dlcEnabled;
    }

    public Boolean getMultiPlayer () {
        return multiPlayer;
    }

    public void setMultiPlayer (Boolean multiPlayer) {
        this.multiPlayer = multiPlayer;
    }

    public Date getStartDate () {
        return startDate;
    }

    public void setStartDate (Date startDate) {
        this.startDate = startDate;
    }

    public List<MapAreaData> getMapAreaData () {
        return mapAreaData;
    }

    public void setMapAreaData (List<MapAreaData> mapAreaData) {
        this.mapAreaData = mapAreaData;
    }

    public List<Institution> getInstitutions () {
        return institutions;
    }

    public void setInstitutions (List<Institution> institutions) {
        this.institutions = institutions;
    }

    public List<String> getProductionsLeader () {
        return productionsLeader;
    }

    public void setProductionsLeader (List<String> productionsLeader) {
        this.productionsLeader = productionsLeader;
    }

    public List<GreatPower> getGreatPowers () {
        return greatPowers;
    }

    public void setGreatPowers (List<GreatPower> greatPowers) {
        this.greatPowers = greatPowers;
    }

    public Empire getEmpire () {
        return empire;
    }

    public void setEmpire (Empire empire) {
        this.empire = empire;
    }

    public Empire getCelestialEmpire () {
        return celestialEmpire;
    }

    public void setCelestialEmpire (Empire celestialEmpire) {
        this.celestialEmpire = celestialEmpire;
    }

    public List<Province> getProvinces () {
        return provinces;
    }

    public void setProvinces (List<Province> provinces) {
        this.provinces = provinces;
    }

    public List<Country> getCountries () {
        return countries;
    }

    public void setCountries (List<Country> countries) {
        this.countries = countries;
    }

    public SortedSet<War> getWars() {
        return wars;
    }

    public void setWars(SortedSet<War> wars) {
        this.wars = wars;
    }
}
