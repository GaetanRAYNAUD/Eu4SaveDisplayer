package com.graynaud.eu4savedisplayerbo.model.save.province;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Province implements Serializable {

    private Integer id = 0;

    private String name;

    private String owner;

    private String controller;

    private List<Double> institutions = Arrays.asList(0d, 0d, 0d, 0d, 0d, 0d, 0d);

    private Integer estate;

    private List<String> cores;

    private String culture;

    private String religion;

    private Double baseTax = 0d;

    private Double baseProduction = 0d;

    private Double baseManpower = 0d;

    private String tradeGood;

    private Double localAutonomy = 0d;

    private List<Building> buildings = new ArrayList<>();

    private Double tradePower = 0d;

    private Integer centerOfTrade = 0;

    private Boolean hre = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null) {
            throw new NullPointerException();
        }

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public List<Double> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<Double> institutions) {
        if (institutions == null || institutions.isEmpty()) {
            this.institutions = Arrays.asList(0d, 0d, 0d, 0d, 0d, 0d, 0d);
        } else {
            this.institutions = institutions;
        }
    }

    public Integer getEstate() {
        return estate;
    }

    public void setEstate(Integer estate) {
        this.estate = estate;
    }

    public List<String> getCores() {
        return cores;
    }

    public void setCores(List<String> cores) {
        this.cores = cores;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Double getBaseTax() {
        return baseTax;
    }

    public void setBaseTax(Double baseTax) {
        if (baseTax == null) {
            this.baseTax = 0d;
        } else {
            this.baseTax = baseTax;
        }
    }

    public Double getBaseProduction() {
        return baseProduction;
    }

    public void setBaseProduction(Double baseProduction) {
        if (baseProduction == null) {
            this.baseProduction = 0d;
        } else {
            this.baseProduction = baseProduction;
        }
    }

    public Double getBaseManpower() {
        return baseManpower;
    }

    public void setBaseManpower(Double baseManpower) {
        if (baseManpower == null) {
            this.baseManpower = 0d;
        } else {
            this.baseManpower = baseManpower;
        }
    }

    public String getTradeGood() {
        return tradeGood;
    }

    public void setTradeGood(String tradeGood) {
        this.tradeGood = tradeGood;
    }

    public Double getLocalAutonomy() {
        return localAutonomy;
    }

    public void setLocalAutonomy(Double localAutonomy) {
        if (localAutonomy == null) {
            this.localAutonomy = 0d;
        } else {
            this.localAutonomy = localAutonomy;
        }
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        if (this.buildings == null) {
            this.buildings = new ArrayList<>();
        } else {
            this.buildings = buildings;
        }
    }

    public Double getTradePower() {
        return tradePower;
    }

    public void setTradePower(Double tradePower) {
        if (tradePower == null) {
            this.tradePower = 0d;
        } else {
            this.tradePower = tradePower;
        }
    }

    public Integer getCenterOfTrade() {
        return centerOfTrade;
    }

    public void setCenterOfTrade(Integer centerOfTrade) {
        this.centerOfTrade = centerOfTrade;
    }

    public Boolean getHre() {
        return hre;
    }

    public void setHre(Boolean hre) {
        if (hre == null) {
            this.hre = false;
        } else {
            this.hre = hre;
        }
    }
}
