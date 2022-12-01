package com.patrickhogg.murdermystery.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class DialogueList implements Serializable {
    private LinkedList<Dialogue> dialogues = new LinkedList<>();

    public DialogueList() {
        // empty constructor
    }

    public LinkedList<Dialogue> getDialogues() {
        return dialogues;
    }

    public void setDialogues(List<Dialogue> dialogues) {
        this.dialogues = (LinkedList<Dialogue>) dialogues;
    }

    public void addDialogue(Dialogue dialogue) {
        if (!dialogues.isEmpty()) {
            dialogues.addLast(dialogue);
        } else {
            dialogues.addFirst(dialogue);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DialogueList that)) {
            return false;
        }
        return Objects.equals(getDialogues(), that.getDialogues());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDialogues());
    }

    @Override
    public String toString() {
        return "DialogueList{" + "dialogues=" + dialogues + '}';
    }
}
