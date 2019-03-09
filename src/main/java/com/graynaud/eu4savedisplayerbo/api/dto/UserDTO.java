package com.graynaud.eu4savedisplayerbo.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.graynaud.eu4savedisplayerbo.model.User;
import com.graynaud.eu4savedisplayerbo.utils.DateUtils;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO implements Serializable {

    private String pseudo;

    private String steamId;

    private String creationDate;

    @JsonIgnoreProperties("author")
    private Set<CampaignDTO> campaigns;

    @JsonIgnoreProperties("players")
    private Set<SaveDTO> savesPayedIn;

    public UserDTO (User author) {
        this.pseudo = author.getPseudo();
        this.steamId = author.getSteamId();
        this.creationDate = DateUtils.convertDateToStringDTO(author.getCreationDate());
        this.campaigns = author.getCampaigns().stream().map(CampaignDTO::new).collect(Collectors.toSet());
        this.savesPayedIn = author.getSavesPayedIn().stream().map(SaveDTO::new).collect(Collectors.toSet());
    }

    public String getPseudo () {
        return pseudo;
    }

    public void setPseudo (String pseudo) {
        this.pseudo = pseudo;
    }

    public String getSteamId () {
        return steamId;
    }

    public void setSteamId (String steamId) {
        this.steamId = steamId;
    }

    public String getCreationDate () {
        return creationDate;
    }

    public void setCreationDate (String creationDate) {
        this.creationDate = creationDate;
    }

    public Set<CampaignDTO> getCampaigns () {
        return campaigns;
    }

    public void setCampaigns (Set<CampaignDTO> campaigns) {
        this.campaigns = campaigns;
    }

    public Set<SaveDTO> getSavesPayedIn () {
        return savesPayedIn;
    }

    public void setSavesPayedIn (Set<SaveDTO> savesPayedIn) {
        this.savesPayedIn = savesPayedIn;
    }

    @Override
    public String toString () {
        return "UserDTO{" +
                "pseudo='" + pseudo + '\'' +
                ", steamId='" + steamId + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", campaigns=" + campaigns +
                ", savesPayedIn=" + savesPayedIn +
                '}';
    }
}
