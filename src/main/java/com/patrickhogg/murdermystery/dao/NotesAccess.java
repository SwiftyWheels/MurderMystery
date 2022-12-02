package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.Note;

/**
 * @author Patrick Hogg
 */
public interface NotesAccess {

    Note getNote();
    String getNoteText();
    void setNote(String text);
}
