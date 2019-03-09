package com.graynaud.eu4savedisplayerbo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "campaign")
@EntityListeners(AuditingEntityListener.class)
public class Campaign implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date creationDate;

    @ManyToOne(optional = false)
    private User author;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "campaign")
    private Set<Save> saves = new HashSet<>();

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
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

    public Set<Save> getSaves () {
        return saves;
    }

    public void setSaves (Set<Save> saves) {
        this.saves = saves;
    }
}
