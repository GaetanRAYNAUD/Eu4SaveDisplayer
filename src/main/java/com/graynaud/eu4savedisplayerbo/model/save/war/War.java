package com.graynaud.eu4savedisplayerbo.model.save.war;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class War implements Comparable<War> , Serializable {
    private String name;

    private Boolean active;

    private Date startingDate;

    private Date endingDate;

    private List<Participant> attackers;

    private List<Participant> defenders;

    private String originalAttacker;

    private String originalDefender;

    private Set<Battle> battles;

    private Integer outcome;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Boolean getActive () {
        return active;
    }

    public void setActive (Boolean active) {
        this.active = active;
    }

    public Date getStartingDate () {
        return startingDate;
    }

    public void setStartingDate (Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate () {
        return endingDate;
    }

    public void setEndingDate (Date endingDate) {
        this.endingDate = endingDate;
    }

    public List<Participant> getAttackers () {
        return attackers;
    }

    public void setAttackers (List<Participant> attackers) {
        this.attackers = attackers;
    }

    public List<Participant> getDefenders () {
        return defenders;
    }

    public void setDefenders (List<Participant> defenders) {
        this.defenders = defenders;
    }

    public String getOriginalAttacker () {
        return originalAttacker;
    }

    public void setOriginalAttacker (String originalAttacker) {
        this.originalAttacker = originalAttacker;
    }

    public String getOriginalDefender () {
        return originalDefender;
    }

    public void setOriginalDefender (String originalDefender) {
        this.originalDefender = originalDefender;
    }

    public Set<Battle> getBattles () {
        return battles;
    }

    public void setBattles (Set<Battle> battles) {
        this.battles = battles;
    }

    public Integer getOutcome () {
        return outcome;
    }

    public void setOutcome (Integer outcome) {
        this.outcome = outcome;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        War war = (War) o;
        return Objects.equals(name, war.name) &&
                Objects.equals(active, war.active) &&
                Objects.equals(startingDate, war.startingDate) &&
                Objects.equals(endingDate, war.endingDate) &&
                Objects.equals(attackers, war.attackers) &&
                Objects.equals(defenders, war.defenders) &&
                Objects.equals(originalAttacker, war.originalAttacker) &&
                Objects.equals(originalDefender, war.originalDefender) &&
                Objects.equals(battles, war.battles) &&
                Objects.equals(outcome, war.outcome);
    }

    @Override
    public int hashCode () {
        return Objects.hash(name, active, startingDate, endingDate, attackers, defenders, originalAttacker, originalDefender, battles, outcome);
    }

    @Override
    public int compareTo (War other) {
        return startingDate.compareTo(other.startingDate);
    }

    @Override
    public String toString () {
        return "War{" +
                "name='" + name + '\'' +
                ", active=" + active +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", attackers=" + attackers +
                ", defenders=" + defenders +
                ", originalAttacker='" + originalAttacker + '\'' +
                ", originalDefender='" + originalDefender + '\'' +
                ", battles=" + battles +
                ", outcome=" + outcome +
                '}';
    }
}
