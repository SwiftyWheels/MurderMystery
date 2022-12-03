package com.patrickhogg.murdermystery.service;

import com.patrickhogg.murdermystery.dao.NotesAccessImpl;
import com.patrickhogg.murdermystery.model.Note;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class NotesAccessServiceImpl implements NotesAccessService,
                                               Serializable {

    private NotesAccessImpl notesAccess = new NotesAccessImpl();

    @Override
    public Note getNote() {
        return notesAccess.getNote();
    }

    @Override
    public String getNoteText() {
        return notesAccess.getNoteText();
    }

    @Override
    public void setNote(String text) {
        notesAccess.setNote(text);
    }

    public NotesAccessImpl getNotesAccess() {
        return notesAccess;
    }

    public void setNotesAccess(NotesAccessImpl notesAccess) {
        this.notesAccess = notesAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotesAccessServiceImpl that)) {
            return false;
        }
        return Objects.equals(getNotesAccess(), that.getNotesAccess());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNotesAccess());
    }

    @Override
    public String toString() {
        return "NotesAccessServiceImpl{" + "notesAccess=" + notesAccess + '}';
    }
}
