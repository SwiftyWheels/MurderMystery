package com.patrickhogg.murdermystery.controllers;

import com.patrickhogg.murdermystery.model.Note;
import com.patrickhogg.murdermystery.model.Player;
import com.patrickhogg.murdermystery.service.NotesAccessServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author Patrick Hogg
 */

@RestController
@RequestMapping("/api/notes")
public class RESTNotesAccessController {
    @GetMapping("/getNotes")
    @ResponseBody
    public Note getNotes(HttpSession session){
        Player player = (Player) session.getAttribute("player");
        NotesAccessServiceImpl notesAccess = player.getNotesAccessService();
        Note note = notesAccess.getNote();
        System.out.println("note: " + note);
        return note;
    }

    @GetMapping("/setNoteText/{text}")
    @ResponseBody
    public void setNoteText(@PathVariable String text, HttpSession session){
        Player player = (Player) session.getAttribute("player");
        NotesAccessServiceImpl notesAccess = player.getNotesAccessService();
        notesAccess.setNote(text);
    }
}
