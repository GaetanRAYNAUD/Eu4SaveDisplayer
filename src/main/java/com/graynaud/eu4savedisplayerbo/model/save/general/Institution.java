package com.graynaud.eu4savedisplayerbo.model.save.general;

public class Institution {
    private Boolean enabled;

    private Integer origin;

    private Double penalties;

    public Boolean getEnabled () {
        return enabled;
    }

    public void setEnabled (Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getOrigin () {
        return origin;
    }

    public void setOrigin (Integer origin) {
        this.origin = origin;
    }

    public Double getPenalties () {
        return penalties;
    }

    public void setPenalties (Double penalties) {
        this.penalties = penalties;
    }

    @Override
    public String toString () {
        return "Institution{" +
                "enabled=" + enabled +
                ", origin=" + origin +
                ", penalties=" + penalties +
                '}';
    }
}
