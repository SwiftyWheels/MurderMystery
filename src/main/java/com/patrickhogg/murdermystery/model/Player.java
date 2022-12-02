package com.patrickhogg.murdermystery.model;

import com.patrickhogg.murdermystery.dao.NotesAccessImpl;
import com.patrickhogg.murdermystery.dao.PersonAccessImpl;
import com.patrickhogg.murdermystery.dao.PersonDialogueAccessImpl;
import com.patrickhogg.murdermystery.service.NotesAccessServiceImpl;
import com.patrickhogg.murdermystery.service.PersonAccessServiceImpl;
import com.patrickhogg.murdermystery.service.PersonDialogueAccessServiceImpl;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class Player implements Serializable, Character {
    private String name;
    private int age;
    private String profession;
    private PersonAccessServiceImpl personAccessService
            = new PersonAccessServiceImpl(new PersonAccessImpl());

    private NotesAccessServiceImpl notesAccessService
            = new NotesAccessServiceImpl(new NotesAccessImpl());

    private PersonDialogueAccessServiceImpl personDialogueAccessService
            = new PersonDialogueAccessServiceImpl(
            new PersonDialogueAccessImpl(personAccessService));

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

    public PersonAccessServiceImpl getPersonAccessService() {
        return personAccessService;
    }

    public void setPersonAccessService(
            PersonAccessServiceImpl personAccessService) {
        this.personAccessService = personAccessService;
    }

    public NotesAccessServiceImpl getNotesAccessService() {
        return notesAccessService;
    }

    public void setNotesAccessService(
            NotesAccessServiceImpl notesAccessService) {
        this.notesAccessService = notesAccessService;
    }

    public PersonDialogueAccessServiceImpl getPersonDialogueAccessService() {
        return personDialogueAccessService;
    }

    public void setPersonDialogueAccessService(
            PersonDialogueAccessServiceImpl personDialogueAccessService) {
        this.personDialogueAccessService = personDialogueAccessService;
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
               && Objects.equals(getProfession(), player.getProfession())
               && Objects.equals(getPersonAccessService(),
                                 player.getPersonAccessService())
               && Objects.equals(getNotesAccessService(),
                                 player.getNotesAccessService())
               && Objects.equals(getPersonDialogueAccessService(),
                                 player.getPersonDialogueAccessService());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getProfession(),
                            getPersonAccessService(), getNotesAccessService(),
                            getPersonDialogueAccessService());
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' + ", age=" + age
               + ", profession='" + profession + '\'' + ", personAccessService="
               + personAccessService + ", notesAccessService="
               + notesAccessService + ", personDialogueAccessService="
               + personDialogueAccessService + '}';
    }
}
