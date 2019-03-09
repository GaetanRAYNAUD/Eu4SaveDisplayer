package com.graynaud.eu4savedisplayerbo.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.graynaud.eu4savedisplayerbo.model.Save;
import com.graynaud.eu4savedisplayerbo.model.User;
import com.graynaud.eu4savedisplayerbo.utils.DateUtils;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

public class SaveDTO implements Serializable {

    private String date;

    private String version;

    private String file;

    private String creationDate;

    @JsonIgnoreProperties("saves")
    private String author;

    @JsonIgnoreProperties("saves")
    private String campaign;

    @JsonIgnoreProperties("savesPayedIn")
    private Set<String> players;

    public SaveDTO (Save save) {
        this.date = DateUtils.convertDateToStringDTO(save.getDate());
        this.version = save.getVersion();
        this.file = save.getFile();
        this.creationDate = DateUtils.convertDateToStringDTO(save.getCreationDate());
        this.author = save.getAuthor().getPseudo();
        this.campaign = save.getCampaign().getName();
        this.players = save.getPlayers().stream().map(User::getPseudo).collect(Collectors.toSet());
    }

    public String getDate () {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getVersion () {
        return version;
    }

    public void setVersion (String version) {
        this.version = version;
    }

    public String getFile () {
        return file;
    }

    public void setFile (String file) {
        this.file = file;
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

    public String getCampaign () {
        return campaign;
    }

    public void setCampaign (String campaign) {
        this.campaign = campaign;
    }

    public Set<String> getPlayers () {
        return players;
    }

    public void setPlayers (Set<String> players) {
        this.players = players;
    }

    @Override
    public String toString () {
        return "SaveDTO{" +
                "date='" + date + '\'' +
                ", version='" + version + '\'' +
                ", file='" + file + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", author=" + author +
                ", campaign=" + campaign +
                ", players=" + players +
                '}';
    }
}
