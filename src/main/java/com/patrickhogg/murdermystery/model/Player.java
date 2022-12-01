package com.patrickhogg.murdermystery.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class Player implements Serializable, Character {
    private String name;
    private int age;
    private String profession;

    public Player() {
        // no args constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player player)) {
            return false;
        }
        return getAge() == player.getAge() && Objects.equals(getName(),
                                                             player.getName())
               && Objects.equals(getProfession(), player.getProfession());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getProfession());
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' + ", age=" + age
               + ", profession='" + profession + '\'' + '}';
    }
}
