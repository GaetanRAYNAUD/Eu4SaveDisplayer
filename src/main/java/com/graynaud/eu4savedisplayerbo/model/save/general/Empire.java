package com.graynaud.eu4savedisplayerbo.model.save.general;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;

public class Empire implements Serializable {
    private String emperor;

    private Double imperialInfluence;

    private Integer reformLevel;

    private SortedSet<OldEmperor> oldEmperors;

    private List<String> electors;

    private EmpireType type;

    public String getEmperor() {
        return emperor;
    }

    public void setEmperor(String emperor) {
        this.emperor = emperor;
    }

    public Double getImperialInfluence() {
        return imperialInfluence;
    }

    public void setImperialInfluence(Double imperialInfluence) {
        this.imperialInfluence = imperialInfluence;
    }

    public Integer getReformLevel() {
        return reformLevel;
    }

    public void setReformLevel(Integer reformLevel) {
        this.reformLevel = reformLevel;
    }

    public SortedSet<OldEmperor> getOldEmperors() {
        return oldEmperors;
    }

    public void setOldEmperors(SortedSet<OldEmperor> oldEmperors) {
        this.oldEmperors = oldEmperors;
    }

    public List<String> getElectors() {
        return electors;
    }

    public void setElectors(List<String> electors) {
        this.electors = electors;
    }

    public EmpireType getType() {
        return type;
    }

    public void setType(EmpireType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Empire empire = (Empire) o;
        return Objects.equals(emperor, empire.emperor) &&
               Objects.equals(imperialInfluence, empire.imperialInfluence) &&
               Objects.equals(reformLevel, empire.reformLevel) &&
               Objects.equals(oldEmperors, empire.oldEmperors) &&
               Objects.equals(electors, empire.electors) &&
               type == empire.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(emperor, imperialInfluence, reformLevel, oldEmperors, electors, type);
    }

    @Override
    public String toString() {
        return "Empire{" +
               "emperor='" + emperor + '\'' +
               ", imperialInfluence=" + imperialInfluence +
               ", reformLevel=" + reformLevel +
               ", oldEmperors=" + oldEmperors +
               ", electors=" + electors +
               ", type=" + type +
               '}';
    }
}
