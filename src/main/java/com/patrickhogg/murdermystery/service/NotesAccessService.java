package com.patrickhogg.murdermystery.service;

import com.patrickhogg.murdermystery.model.Note;

/**
 * @author Patrick Hogg
 */
public interface NotesAccessService {
    Note getNote();
    String getNoteText();
    void setNote(String text);
}
