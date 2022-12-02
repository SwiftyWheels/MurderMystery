package com.patrickhogg.murdermystery.service;

import com.patrickhogg.murdermystery.dao.NotesAccessImpl;
import com.patrickhogg.murdermystery.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Patrick Hogg
 */
@Service
public class NotesAccessServiceImpl implements NotesAccessService{

    @Autowired
    NotesAccessImpl notesAccess;

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
}
