package com.graynaud.eu4savedisplayerbo.api.input;

import javax.validation.constraints.NotBlank;

public class CampaignCreate {

    @NotBlank
    private String name;

    @NotBlank
    private String author;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getAuthor () {
        return author;
    }

    public void setAuthor (String author) {
        this.author = author;
    }

    @Override
    public String toString () {
        return "CampaignCreate{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
