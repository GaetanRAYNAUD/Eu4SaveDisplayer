package com.graynaud.eu4savedisplayerbo.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.graynaud.eu4savedisplayerbo.model.Campaign;
import com.graynaud.eu4savedisplayerbo.utils.DateUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class CampaignDTO {

    private String name;

    private String creationDate;

    @JsonIgnoreProperties("campaigns")
    private String author;

    @JsonIgnoreProperties("campaign")
    private Set<SaveDTO> saves;

    public CampaignDTO (Campaign campaign) {
        this.name = campaign.getName();
        this.creationDate = DateUtils.convertDateToStringDTO(campaign.getCreationDate());
        this.author = campaign.getAuthor().getPseudo();
        this.saves = campaign.getSaves().stream().map(SaveDTO::new).collect(Collectors.toSet());
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getCreationDate () {
        return creationDate;
    }

    public void setCreationDate (String creationDate) {
        this.creationDate = creationDate;
    }

    public String getAuthor () {
        return author;
    }

    public void setAuthor (String author) {
        this.author = author;
    }

    public Set<SaveDTO> getSaves () {
        return saves;
    }

    public void setSaves (Set<SaveDTO> saves) {
        this.saves = saves;
    }

    @Override
    public String toString () {
        return "CampaignDTO{" +
                "name='" + name + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", author=" + author +
                ", saves=" + saves +
                '}';
    }
}
