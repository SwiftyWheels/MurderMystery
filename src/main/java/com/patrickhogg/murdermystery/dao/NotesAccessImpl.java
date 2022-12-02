package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.Note;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */

public class NotesAccessImpl implements NotesAccess, Serializable {
    private final Note note;

    public NotesAccessImpl() {
        this.note = new Note();
    }

    @Override
    public Note getNote() {
        return note;
    }

    @Override
    public String getNoteText() {
        return getNote().getNotes();
    }

    @Override
    public void setNote(String text) {
        getNote().setNotes(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotesAccessImpl that)) {
            return false;
        }
        return Objects.equals(getNote(), that.getNote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNote());
    }

    @Override
    public String toString() {
        return "NotesAccessImpl{" + "note=" + note + '}';
    }
}
