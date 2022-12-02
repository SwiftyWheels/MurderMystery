package com.patrickhogg.murdermystery.controllers;

import com.patrickhogg.murdermystery.model.Dialogue;
import com.patrickhogg.murdermystery.service.PersonDialogueAccessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Patrick Hogg
 */
@RestController
@RequestMapping("/api/dialogue")
public class RESTDialogueAccessController {
    @Autowired
    private PersonDialogueAccessServiceImpl dialogueAccess;

    @GetMapping("/getPersonDialogue/{personName}/{id}")
    @ResponseBody
    public Dialogue getPersonDialogByID(@PathVariable String personName,
                                        @PathVariable int id) {
        Dialogue dialogue = dialogueAccess.getPersonDialogueByDialogueID(
                personName, id);
        if (dialogue != null) {
            dialogueAccess.setPersonDialogueAsRead(personName, dialogue);
            return dialogue;
        }
        return null;
    }

    @GetMapping("/getPersonDialogue/{personName}")
    @ResponseBody
    public Dialogue getPersonDialog(@PathVariable String personName) {
        Dialogue dialogue = dialogueAccess.getPersonNextUnreadDialogue(
                personName);
        if (dialogue != null) {
            dialogueAccess.setPersonDialogueAsRead(personName, dialogue);
            return dialogue;
        }
        return null;
    }
}
