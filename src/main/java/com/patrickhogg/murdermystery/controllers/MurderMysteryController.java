package com.patrickhogg.murdermystery.controllers;

import com.patrickhogg.murdermystery.dao.ResourceFileReaderImpl;
import com.patrickhogg.murdermystery.model.DialogueList;
import com.patrickhogg.murdermystery.model.Note;
import com.patrickhogg.murdermystery.model.Person;
import com.patrickhogg.murdermystery.model.Player;
import com.patrickhogg.murdermystery.model.enums.Gender;
import com.patrickhogg.murdermystery.service.NotesAccessService;
import com.patrickhogg.murdermystery.service.PersonAccessServiceImpl;
import com.patrickhogg.murdermystery.service.PersonDialogueAccessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.LinkedList;
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

    @Autowired
    private NotesAccessService notesAccess;

    @GetMapping("/")
    public String getIndex(HttpSession session, Model model) {
        session.setAttribute("player", new Player());
        session.setAttribute("hasStarted",false);
        Player player = (Player) session.getAttribute("player");
        notesAccess.setNote("Enter notes here...");

        model.addAttribute("player", player);
        return "index";
    }

    @PostMapping("/startGame")
    public String getStartGame(@ModelAttribute Player player, Model model,
                               HttpSession session) {
        session.setAttribute("player", player);
        Note note = notesAccess.getNote();
        System.out.println(note );
        boolean hasStarted = (boolean) session.getAttribute("hasStarted");

        if ((personAccess.getAllPeople().isEmpty()) || !hasStarted) {
            initPeople(session);
            System.out.println("Initializing people");
            session.setAttribute("hasStarted",true);
        }
        if(note.getNotes() != null && !note.getNotes().isEmpty()){
            model.addAttribute("notes", note);
        }

        Player gamePlayer = (Player) session.getAttribute("player");
        model.addAttribute("player", gamePlayer);
        return "intro-game";
    }


    public void initPeople(HttpSession session) {
        List<Person> personList = new LinkedList<>();
        Person narrator = new Person("Narrator", Gender.MALE, false, null);
        personList.add(narrator);
        Person joe = new Person("Joe", Gender.MALE, false, null);
        personList.add(joe);
        Person debbie = new Person("Debbie", Gender.FEMALE, false, null);
        personList.add(debbie);
        Person diane = new Person("Diane", Gender.FEMALE, false, null);
        personList.add(diane);
        Person eve = new Person("Eve", Gender.FEMALE, false, null);
        personList.add(eve);

        for (Person person : personList) {
            personAccess.addPerson(person);
        }
        initDialogue(session);
    }

    public void initDialogue(HttpSession session) {
        try {
            ResourceFileReaderImpl resourceFileReader
                    = new ResourceFileReaderImpl();

            // NARRATOR
            File narratorFile = new ClassPathResource(
                    "/dialogues/characters/narrator/dialogue.txt").getFile();

            DialogueList narratorDialogue
                    = resourceFileReader.getDialogueListFromFile(narratorFile,
                                                                 session);

            Person narrator = personAccess.getPersonByName("Narrator");
            narrator.setDialogueList(narratorDialogue);

            // JOE
            // DIANE
            // DEBBIE
            // EVE
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
