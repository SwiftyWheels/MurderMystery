package com.patrickhogg.murdermystery.service;

import com.patrickhogg.murdermystery.dao.PersonDialogueAccessImpl;
import com.patrickhogg.murdermystery.model.Dialogue;
import com.patrickhogg.murdermystery.model.DialogueList;
import com.patrickhogg.murdermystery.model.Person;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class PersonDialogueAccessServiceImpl
        implements PersonDialogueAccessService, Serializable {
    private final PersonDialogueAccessImpl personDialogueAccess;

    public PersonDialogueAccessServiceImpl(
            PersonDialogueAccessImpl personDialogueAccess) {
        this.personDialogueAccess = personDialogueAccess;
    }

    @Override
    public DialogueList getPersonDialogueList(Person person) {
        return personDialogueAccess.getPersonDialogueList(person);
    }

    @Override
    public DialogueList getPersonDialogueList(String personName) {
        return personDialogueAccess.getPersonDialogueList(personName);
    }

    @Override
    public Dialogue getPersonDialogueByDialogueID(Person person,
                                                  int dialogueID) {
        return personDialogueAccess.getPersonDialogueByDialogueID(person,
                                                                  dialogueID);
    }

    @Override
    public Dialogue getPersonDialogueByDialogueID(String personName,
                                                  int dialogueID) {
        return personDialogueAccess.getPersonDialogueByDialogueID(personName,
                                                                  dialogueID);
    }

    @Override
    public Dialogue getPersonNextUnreadDialogue(Person person) {
        Dialogue dialogue = personDialogueAccess.getPersonNextUnreadDialogue(
                person);
        if (dialogue == null) {
            return personDialogueAccess.getPersonDialogueList(person)
                                       .getDialogues()
                                       .peekLast();
        }
        return dialogue;
    }

    @Override
    public Dialogue getPersonNextUnreadDialogue(String personName) {
        Dialogue dialogue = personDialogueAccess.getPersonNextUnreadDialogue(
                personName);
        if (dialogue == null) {
            return personDialogueAccess.getPersonDialogueList(personName)
                                       .getDialogues()
                                       .peekLast();
        }
        return dialogue;
    }

    @Override
    public Dialogue getPersonDialogue(Person person, Dialogue dialogue) {
        return personDialogueAccess.getPersonDialogue(person, dialogue);
    }

    @Override
    public Dialogue getPersonDialogue(String personName, Dialogue dialogue) {
        return personDialogueAccess.getPersonDialogue(personName, dialogue);
    }

    @Override
    public void setPersonDialogueAsRead(Person person, Dialogue dialogue) {
        personDialogueAccess.setPersonDialogueAsRead(person, dialogue);
    }

    @Override
    public void setPersonDialogueAsRead(String personName, Dialogue dialogue) {
        personDialogueAccess.setPersonDialogueAsRead(personName, dialogue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonDialogueAccessServiceImpl that)) {
            return false;
        }
        return Objects.equals(personDialogueAccess, that.personDialogueAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personDialogueAccess);
    }

    @Override
    public String toString() {
        return "PersonDialogueAccessServiceImpl{" + "personDialogueAccess="
               + personDialogueAccess + '}';
    }
}
