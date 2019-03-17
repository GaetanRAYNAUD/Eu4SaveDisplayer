package com.graynaud.eu4savedisplayerbo.model.save.general;

import java.io.Serializable;
import java.util.Objects;

public class SaveGameVersion implements Serializable {

    private Integer first;

    private Integer second;

    private Integer third;

    private Integer forth;

    public Integer getFirst () {
        return first;
    }

    public void setFirst (Integer first) {
        this.first = first;
    }

    public Integer getSecond () {
        return second;
    }

    public void setSecond (Integer second) {
        this.second = second;
    }

    public Integer getThird () {
        return third;
    }

    public void setThird (Integer third) {
        this.third = third;
    }

    public Integer getForth () {
        return forth;
    }

    public void setForth (Integer forth) {
        this.forth = forth;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SaveGameVersion that = (SaveGameVersion) o;
        return Objects.equals(first, that.first) &&
                Objects.equals(second, that.second) &&
                Objects.equals(third, that.third) &&
                Objects.equals(forth, that.forth);
    }

    @Override
    public int hashCode () {
        return Objects.hash(first, second, third, forth);
    }

    @Override
    public String toString () {
        return first + "." + second + "." + third + "." + forth;
    }
}
