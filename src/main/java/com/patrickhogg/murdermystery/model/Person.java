package com.patrickhogg.murdermystery.model;

import com.patrickhogg.murdermystery.model.enums.Gender;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class Person implements Serializable, Character {
    private String name;
    private Gender gender;
    private boolean isMurderer;
    private DialogueList dialogueList;

    public Person(String name, Gender gender, boolean isMurderer,
                  DialogueList dialogues) {
        this.name = name;
        this.gender = gender;
        this.isMurderer = isMurderer;
        this.dialogueList = dialogues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isMurderer() {
        return isMurderer;
    }

    public void setMurderer(boolean murderer) {
        isMurderer = murderer;
    }

    public DialogueList getDialogueList() {
        return dialogueList;
    }

    public void setDialogueList(DialogueList dialogueList) {
        this.dialogueList = dialogueList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person person)) {
            return false;
        }
        return isMurderer() == person.isMurderer() && Objects.equals(getName(),
                                                                     person.getName())
               && getGender() == person.getGender();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getGender(), isMurderer());
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", gender=" + gender
               + ", isMurderer=" + isMurderer + ", dialogues=" + dialogueList
               + '}';
    }
}
