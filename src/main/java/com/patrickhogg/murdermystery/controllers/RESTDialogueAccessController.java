package com.patrickhogg.murdermystery.controllers;

import com.patrickhogg.murdermystery.model.Dialogue;
import com.patrickhogg.murdermystery.model.Player;
import com.patrickhogg.murdermystery.service.PersonDialogueAccessServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author Patrick Hogg
 */
@RestController
@RequestMapping("/api/dialogue")
public class RESTDialogueAccessController {

    @GetMapping("/getPersonDialogue/{personName}/{id}")
    @ResponseBody
    public Dialogue getPersonDialogByID(@PathVariable String personName,
                                        @PathVariable int id, HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        PersonDialogueAccessServiceImpl dialogueAccess = player.getPersonDialogueAccessService();
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
    public Dialogue getPersonDialog(@PathVariable String personName, HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        PersonDialogueAccessServiceImpl dialogueAccess = player.getPersonDialogueAccessService();
        Dialogue dialogue = dialogueAccess.getPersonNextUnreadDialogue(
                personName);
        if (dialogue != null) {
            dialogueAccess.setPersonDialogueAsRead(personName, dialogue);
            return dialogue;
        }
        return null;
    }
}
