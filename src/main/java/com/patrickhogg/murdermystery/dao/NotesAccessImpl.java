package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.Note;
import org.springframework.stereotype.Repository;

/**
 * @author Patrick Hogg
 */

@Repository
public class NotesAccessImpl implements NotesAccess{
    private Note note = new Note();

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
}
