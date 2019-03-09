package com.graynaud.eu4savedisplayerbo.model.save.war;

public class Fighter {
    private Integer cavalry;

    private Integer artillery;

    private Integer infantry;

    private Integer galley;

    private Integer lightShip;

    private Integer heavyShip;

    private Integer transport;

    private Integer losses;

    private String country;

    private String commander;

    public Integer getCavalry () {
        return cavalry;
    }

    public void setCavalry (Integer cavalry) {
        this.cavalry = cavalry;
    }

    public Integer getArtillery () {
        return artillery;
    }

    public void setArtillery (Integer artillery) {
        this.artillery = artillery;
    }

    public Integer getInfantry () {
        return infantry;
    }

    public void setInfantry (Integer infantry) {
        this.infantry = infantry;
    }

    public Integer getGalley () {
        return galley;
    }

    public void setGalley (Integer galley) {
        this.galley = galley;
    }

    public Integer getLightShip () {
        return lightShip;
    }

    public void setLightShip (Integer lightShip) {
        this.lightShip = lightShip;
    }

    public Integer getHeavyShip () {
        return heavyShip;
    }

    public void setHeavyShip (Integer heavyShip) {
        this.heavyShip = heavyShip;
    }

    public Integer getTransport () {
        return transport;
    }

    public void setTransport (Integer transport) {
        this.transport = transport;
    }

    public Integer getLosses () {
        return losses;
    }

    public void setLosses (Integer losses) {
        this.losses = losses;
    }

    public String getCountry () {
        return country;
    }

    public void setCountry (String country) {
        this.country = country;
    }

    public String getCommander () {
        return commander;
    }

    public void setCommander (String commander) {
        this.commander = commander;
    }
}
