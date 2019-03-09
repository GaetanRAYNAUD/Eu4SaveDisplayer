package com.graynaud.eu4savedisplayerbo.model.save.war;

import java.util.Date;
import java.util.Objects;

public class Battle implements Comparable<Battle> {
    private String name;

    private Date date;

    private Integer location;

    private Boolean result;

    private Fighter attacker;

    private Fighter defender;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    public Integer getLocation () {
        return location;
    }

    public void setLocation (Integer location) {
        this.location = location;
    }

    public Boolean getResult () {
        return result;
    }

    public void setResult (Boolean result) {
        this.result = result;
    }

    public Fighter getAttacker () {
        return attacker;
    }

    public void setAttacker (Fighter attacker) {
        this.attacker = attacker;
    }

    public Fighter getDefender () {
        return defender;
    }

    public void setDefender (Fighter defender) {
        this.defender = defender;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Battle battle = (Battle) o;
        return Objects.equals(name, battle.name) &&
                Objects.equals(date, battle.date) &&
                Objects.equals(location, battle.location) &&
                Objects.equals(result, battle.result) &&
                Objects.equals(attacker, battle.attacker) &&
                Objects.equals(defender, battle.defender);
    }

    @Override
    public int hashCode () {
        return Objects.hash(name, date, location, result, attacker, defender);
    }

    @Override
    public int compareTo (Battle other) {
        return date.compareTo(other.date);
    }

    @Override
    public String toString () {
        return "Battle{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", location=" + location +
                ", result=" + result +
                ", attacker=" + attacker +
                ", defender=" + defender +
                '}';
    }
}
