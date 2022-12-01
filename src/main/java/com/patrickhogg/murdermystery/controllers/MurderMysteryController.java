package com.patrickhogg.murdermystery.controllers;

import com.patrickhogg.murdermystery.model.Dialogue;
import com.patrickhogg.murdermystery.model.DialogueList;
import com.patrickhogg.murdermystery.model.Person;
import com.patrickhogg.murdermystery.model.Player;
import com.patrickhogg.murdermystery.model.enums.Gender;
import com.patrickhogg.murdermystery.service.PersonAccessServiceImpl;
import com.patrickhogg.murdermystery.service.PersonDialogueAccessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Patrick Hogg
 */
@Controller
public class MurderMysteryController {

    @Autowired
    private PersonAccessServiceImpl personAccess;

    @Autowired
    private PersonDialogueAccessServiceImpl dialogueAccess;

    @GetMapping("/")
    public String getIndex(HttpSession session, Model model) {
        if ((personAccess.getAllPeople().isEmpty())) {
            initPeople(personAccess.getAllPeople());
            session.setAttribute("hasStarted", true);
            Player player = new Player();
            session.setAttribute("player", player);
        }
        Player player = (Player) session.getAttribute("player");
        model.addAttribute("people", personAccess.getAllPeople());
        model.addAttribute("player", player);
        return "index";
    }

    @PostMapping("/startGame")
    public String getStartGame(@ModelAttribute Player player, Model model,
                               HttpSession session) {
        session.setAttribute("player", player);
        Player gamePlayer = (Player) session.getAttribute("player");
        model.addAttribute("player", gamePlayer);
        return "intro-game";
    }

    @GetMapping("/getPersonDialogue/{personName}/{id}")
    @ResponseBody
    public void getPersonDialogByID(@PathVariable String personName,
                                    @PathVariable int id) {

    }

    @GetMapping("/getPersonDialogue/{personName}")
    @ResponseBody
    public Dialogue getPersonDialog(@PathVariable String personName) {
        Dialogue dialogue = dialogueAccess.getPersonNextUnreadDialogue(personName);
        dialogueAccess.setPersonDialogueAsRead(personName, dialogue);
        return dialogue;
    }

    public void initPeople(List<Person> personList) {
        Person testPerson = new Person("Test", Gender.MALE, false,
                                       initDialogue());
        personList.add(testPerson);
    }

    public DialogueList initDialogue() {
        DialogueList testDialogue = new DialogueList();
        testDialogue.addDialogue(new Dialogue(1, "Test", false));
        testDialogue.addDialogue(new Dialogue(2, "Test2", false));
        testDialogue.addDialogue(new Dialogue(3, "Test3", false));
        return testDialogue;
    }
}
