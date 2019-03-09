package com.graynaud.eu4savedisplayerbo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "steam_id")
    private String steamId;

    @Column(name = "creation_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date creationDate;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    private Set<Campaign> campaigns = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    private Set<Save> saves = new HashSet<>();

    @ManyToMany(mappedBy = "players")
    private Set<Save> savesPayedIn = new HashSet<>();

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
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

    public Date getCreationDate () {
        return creationDate;
    }

    public void setCreationDate (Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Campaign> getCampaigns () {
        return campaigns;
    }

    public void setCampaigns (Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public Set<Save> getSaves () {
        return saves;
    }

    public void setSaves (Set<Save> saves) {
        this.saves = saves;
    }

    public Set<Save> getSavesPayedIn () {
        return savesPayedIn;
    }

    public void setSavesPayedIn (Set<Save> savesPayedIn) {
        this.savesPayedIn = savesPayedIn;
    }
}
