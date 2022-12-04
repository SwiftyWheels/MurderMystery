package com.patrickhogg.murdermystery.controllers;

import com.patrickhogg.murdermystery.dao.ResourceFileReaderImpl;
import com.patrickhogg.murdermystery.model.*;
import com.patrickhogg.murdermystery.model.enums.Gender;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Patrick Hogg
 */
@Controller
public class MurderMysteryController {

    private final ResourceFileReaderImpl resourceFileReader = new ResourceFileReaderImpl();

    @GetMapping("/")
    public String getIndex(HttpSession session, Model model) {
        Player player = new Player();
        System.out.println(player);

        session.setAttribute("player",player);
        session.setAttribute("hasStarted",false);

        model.addAttribute("player", player);
        return "index";
    }

    @PostMapping("/startGame")
    public String getStartGame(@ModelAttribute Player player, Model model,
                               HttpSession session) {

        if ((Player) session.getAttribute("player") == null) {
            return "redirect:/";
        }

        session.setAttribute("player", player);
        player = (Player) session.getAttribute("player");
        player.getNotesAccessService().setNote("Enter notes here...");

        initGameValues(session);

        Note note = player.getNotesAccessService().getNote();
        if(note.getNotes() != null && !note.getNotes().isEmpty()){
            model.addAttribute("notes", note);
        }


        Player gamePlayer = (Player) session.getAttribute("player");
        model.addAttribute("player", gamePlayer);
        return "intro-game";
    }

    public void initGameValues(HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        boolean hasStarted = (boolean) session.getAttribute("hasStarted");

        if ((player.getPersonAccessService().getAllPeople().isEmpty()) || !hasStarted) {
            initPeople(session);
            System.out.println("Initializing people");
            session.setAttribute("hasStarted",true);
        }

        if (player.getEventsAccessService().getEventList().getEvents() == null
            || !hasStarted) {
            initEvents(session);
            System.out.println("Initializing events");
            session.setAttribute("hasStarted",true);
        }

        if (player.getStoryFlagAccessService().getStoryFlagList().getStoryFlags() == null
            || !hasStarted) {
            initStoryFlags(session);
            System.out.println("Initializing story flags");
            session.setAttribute("hasStarted",true);
        }
    }


    public void initPeople(HttpSession session) {
        Player player = (Player) session.getAttribute("player");
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
            player.getPersonAccessService().addPerson(person);
        }
        initDialogue(session);
    }

    public void initDialogue(HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        try {

            // NARRATOR
            InputStream narratorFile = new ClassPathResource(
                    "/dialogues/characters/narrator/dialogue.txt").getInputStream();

            DialogueList narratorDialogue
                    = resourceFileReader.getDialogueListFromFile(narratorFile,
                                                                 session);

            Person narrator = player.getPersonAccessService().getPersonByName("Narrator");
            narrator.setDialogueList(narratorDialogue);

            // JOE
            // DIANE
            // DEBBIE
            // EVE
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initEvents(HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        try {
            InputStream eventsFile = new ClassPathResource(
                    "/events/events.txt").getInputStream();

            EventList eventList = resourceFileReader.getEventListFromFile(
                    eventsFile);

            player.getEventsAccessService().getEventsAccess().setEventList(
                    eventList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initStoryFlags(HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        try {
            InputStream eventsFile = new ClassPathResource(
                    "/events/storyFlags.txt").getInputStream();

            StoryFlagList storyFlagList = resourceFileReader.getStoryFlagListFromFile(
                    eventsFile);

            player.getStoryFlagAccessService().getFlagAccess().setStoryFlagList(
                    storyFlagList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
