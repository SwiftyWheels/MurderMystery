package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.Dialogue;
import com.patrickhogg.murdermystery.model.DialogueList;
import com.patrickhogg.murdermystery.model.Person;
import com.patrickhogg.murdermystery.service.PersonAccessServiceImpl;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class PersonDialogueAccessImpl
        implements PersonDialogueAccess, Serializable {

    private final PersonAccessServiceImpl personAccess;

    public PersonDialogueAccessImpl(PersonAccessServiceImpl personAccess) {
        this.personAccess = personAccess;
    }

    @Override
    public DialogueList getPersonDialogueList(Person person) {
        if (person != null) {
            return personAccess.getPerson(person).getDialogueList();
        }
        return null;
    }

    @Override
    public DialogueList getPersonDialogueList(String personName) {
        return personAccess.getPersonByName(personName).getDialogueList();
    }

    @Override
    public Dialogue getPersonDialogueByDialogueID(Person person,
                                                  int dialogueID) {
        Person personToGet = personAccess.getPerson(person);
        if (personToGet != null) {
            for (Dialogue dialogue : personToGet.getDialogueList()
                                                .getDialogues()) {
                if (dialogue.getId() == dialogueID) {
                    return dialogue;
                }
            }
        }
        return null;
    }

    @Override
    public Dialogue getPersonDialogueByDialogueID(String personName,
                                                  int dialogueID) {
        Person personToGet = personAccess.getPersonByName(personName);
        if (personToGet != null) {
            for (Dialogue dialogue : personToGet.getDialogueList()
                                                .getDialogues()) {
                if (dialogue.getId() == dialogueID) {
                    return dialogue;
                }
            }
        }
        return null;
    }

    @Override
    public Dialogue getPersonNextUnreadDialogue(Person person) {
        Person personToGet = personAccess.getPerson(person);
        if (personToGet != null) {
            for (Dialogue dialogue : personToGet.getDialogueList()
                                                .getDialogues()) {
                if (!dialogue.isRead()) {
                    return dialogue;
                }
            }
        }
        return null;
    }

    @Override
    public Dialogue getPersonNextUnreadDialogue(String personName) {
        Person personToGet = personAccess.getPersonByName(personName);
        if (personToGet != null) {
            for (Dialogue dialogue : personToGet.getDialogueList()
                                                .getDialogues()) {
                if (!dialogue.isRead()) {
                    return dialogue;
                }
            }
        }
        return null;
    }

    @Override
    public Dialogue getPersonDialogue(Person person, Dialogue dialogue) {
        Person personToGet = personAccess.getPerson(person);
        if (personToGet != null) {
            for (Dialogue currentDialogue : personToGet.getDialogueList()
                                                       .getDialogues()) {
                if (currentDialogue.equals(dialogue)) {
                    return currentDialogue;
                }
            }
        }
        return null;
    }

    @Override
    public Dialogue getPersonDialogue(String personName, Dialogue dialogue) {
        Person personToGet = personAccess.getPersonByName(personName);
        if (personToGet != null) {
            for (Dialogue currentDialogue : personToGet.getDialogueList()
                                                       .getDialogues()) {
                if (currentDialogue.equals(dialogue)) {
                    return currentDialogue;
                }
            }
        }
        return null;
    }

    @Override
    public void setPersonDialogueAsRead(Person person, Dialogue dialogue) {
        Person personToGet = personAccess.getPerson(person);
        if (personToGet != null) {
            Dialogue dialogueToGet = getPersonDialogue(personToGet, dialogue);
            dialogueToGet.setRead(true);
        }
    }

    @Override
    public void setPersonDialogueAsRead(String personName, Dialogue dialogue) {
        Person personToGet = personAccess.getPersonByName(personName);
        if (personToGet != null) {
            Dialogue dialogueToGet = getPersonDialogue(personToGet, dialogue);
            dialogueToGet.setRead(true);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonDialogueAccessImpl that)) {
            return false;
        }
        return Objects.equals(personAccess, that.personAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personAccess);
    }
}
