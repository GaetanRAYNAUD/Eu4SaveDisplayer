package com.graynaud.eu4savedisplayerbo.model.save.province;

import java.util.List;

public class Province {
    private Integer id;

    private String name;

    private String owner;

    private String controller;

    private List<Double> institutions;

    private Integer estate;

    private List<String> cores;

    private String culture;

    private String religion;

    private Double baseTax;

    private Double baseProduction;

    private Double baseManpower;

    private String tradeGood;

    private Double localAutonomy;

    private List<Building> buildings;

    private Double tradePower;

    private Integer centerOfTrade;

    private Boolean hre;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getOwner () {
        return owner;
    }

    public void setOwner (String owner) {
        this.owner = owner;
    }

    public String getController () {
        return controller;
    }

    public void setController (String controller) {
        this.controller = controller;
    }

    public List<Double> getInstitutions () {
        return institutions;
    }

    public void setInstitutions (List<Double> institutions) {
        this.institutions = institutions;
    }

    public Integer getEstate () {
        return estate;
    }

    public void setEstate (Integer estate) {
        this.estate = estate;
    }

    public List<String> getCores () {
        return cores;
    }

    public void setCores (List<String> cores) {
        this.cores = cores;
    }

    public String getCulture () {
        return culture;
    }

    public void setCulture (String culture) {
        this.culture = culture;
    }

    public String getReligion () {
        return religion;
    }

    public void setReligion (String religion) {
        this.religion = religion;
    }

    public Double getBaseTax () {
        return baseTax;
    }

    public void setBaseTax (Double baseTax) {
        this.baseTax = baseTax;
    }

    public Double getBaseProduction () {
        return baseProduction;
    }

    public void setBaseProduction (Double baseProduction) {
        this.baseProduction = baseProduction;
    }

    public Double getBaseManpower () {
        return baseManpower;
    }

    public void setBaseManpower (Double baseManpower) {
        this.baseManpower = baseManpower;
    }

    public String getTradeGood () {
        return tradeGood;
    }

    public void setTradeGood (String tradeGood) {
        this.tradeGood = tradeGood;
    }

    public Double getLocalAutonomy () {
        return localAutonomy;
    }

    public void setLocalAutonomy (Double localAutonomy) {
        this.localAutonomy = localAutonomy;
    }

    public List<Building> getBuildings () {
        return buildings;
    }

    public void setBuildings (List<Building> buildings) {
        this.buildings = buildings;
    }

    public Double getTradePower () {
        return tradePower;
    }

    public void setTradePower (Double tradePower) {
        this.tradePower = tradePower;
    }

    public Integer getCenterOfTrade () {
        return centerOfTrade;
    }

    public void setCenterOfTrade (Integer centerOfTrade) {
        this.centerOfTrade = centerOfTrade;
    }

    public Boolean getHre () {
        return hre;
    }

    public void setHre (Boolean hre) {
        this.hre = hre;
    }
}
