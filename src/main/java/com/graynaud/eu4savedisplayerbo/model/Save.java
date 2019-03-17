package com.graynaud.eu4savedisplayerbo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "save")
@EntityListeners(AuditingEntityListener.class)
public class Save implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "version")
    private String version;

    @Column(name = "file")
    private String file;

    @Column(name = "creation_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date creationDate;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    private Campaign campaign;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_save",
            joinColumns = { @JoinColumn(name = "save_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> players = new HashSet<>();

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
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

    public Date getCreationDate () {
        return creationDate;
    }

    public void setCreationDate (Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getAuthor () {
        return author;
    }

    public void setAuthor (User author) {
        this.author = author;
    }

    public Campaign getCampaign () {
        return campaign;
    }

    public void setCampaign (Campaign campaign) {
        this.campaign = campaign;
    }

    public Set<User> getPlayers () {
        return players;
    }

    public void setPlayers (Set<User> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Save{" +
               "id=" + id +
               ", date=" + date +
               ", version='" + version + '\'' +
               ", file='" + file + '\'' +
               ", creationDate=" + creationDate +
               ", author=" + author +
               ", campaign=" + campaign +
               ", players=" + players +
               '}';
    }
}
