package com.graynaud.eu4savedisplayerbo.model.save.province;

import java.io.Serializable;

public class Building implements Serializable {
    private String name;

    private String builder;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getBuilder () {
        return builder;
    }

    public void setBuilder (String builder) {
        this.builder = builder;
    }

    @Override
    public String toString () {
        return "Building{" +
                "name='" + name + '\'' +
                ", builder='" + builder + '\'' +
                '}';
    }
}
