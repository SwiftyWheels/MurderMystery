package com.patrickhogg.murdermystery.controllers;

import com.patrickhogg.murdermystery.model.Note;
import com.patrickhogg.murdermystery.service.NotesAccessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Patrick Hogg
 */

@RestController
@RequestMapping("/api/notes")
public class RESTNotesAccessController {

    @Autowired
    private NotesAccessServiceImpl notesAccess;

    @GetMapping("/getNotes")
    @ResponseBody
    public Note getNotes(){
        Note note = notesAccess.getNote();
        System.out.println("note: " + note);
        return note;
    }

    @GetMapping("/setNoteText/{text}")
    @ResponseBody
    public void setNoteText(@PathVariable String text){
        notesAccess.setNote(text);
    }
}
