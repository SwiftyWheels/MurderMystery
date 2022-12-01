package com.patrickhogg.murdermystery.service;

import com.patrickhogg.murdermystery.model.Dialogue;
import com.patrickhogg.murdermystery.model.DialogueList;
import com.patrickhogg.murdermystery.model.Person;

/**
 * @author Patrick Hogg
 */
public interface PersonDialogueAccessService {
    DialogueList getPersonDialogueList(Person person);

    DialogueList getPersonDialogueList(String personName);

    Dialogue getPersonDialogueByDialogueID(Person person, int dialogueID);

    Dialogue getPersonDialogueByDialogueID(String personName, int dialogueID);

    Dialogue getPersonNextUnreadDialogue(Person person);

    Dialogue getPersonNextUnreadDialogue(String personName);

    Dialogue getPersonDialogue(Person person, Dialogue dialogue);

    Dialogue getPersonDialogue(String personName, Dialogue dialogue);

    void setPersonDialogueAsRead(Person person, Dialogue dialogue);

    void setPersonDialogueAsRead(String personName, Dialogue dialogue);
}
