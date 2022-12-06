package com.patrickhogg.murdermystery.controllers;

import com.patrickhogg.murdermystery.dao.ResourceFileReaderImpl;
import com.patrickhogg.murdermystery.model.*;
import com.patrickhogg.murdermystery.model.enums.Gender;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Patrick Hogg
 */
@Controller
public class MurderMysteryController {

    private final ResourceFileReaderImpl resourceFileReader
            = new ResourceFileReaderImpl();

    @GetMapping("/")
    public String getIndex(HttpSession session, Model model) {
        Player player = new Player();

        if (!session.isNew()) {
            // Invalidate the session
            session.removeAttribute("player");
            session.removeAttribute("hasStarted");
        }

        session.setAttribute("player", player);
        session.setAttribute("hasStarted", false);

        model.addAttribute("player", player);
        return "index";
    }

    @GetMapping("/startGame")
    public String getStartGame(@ModelAttribute Player player, Model model,
                               HttpSession session) {

        if (player == null || player.getName() == null) {
            return "redirect:/";
        }

        session.setAttribute("player", player);
        player = (Player) session.getAttribute("player");
        player.getNotesAccessService().setNote("Enter notes here...");

        initGameValues(session);

        Note note = player.getNotesAccessService().getNote();

        if (note.getNotes() != null && !note.getNotes().isEmpty()) {
            model.addAttribute("notes", note);
        }


        Player gamePlayer = (Player) session.getAttribute("player");
        model.addAttribute("player", gamePlayer);

        return "intro-game";
    }

    public void initGameValues(HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        boolean hasStarted = (boolean) session.getAttribute("hasStarted");

        if ((player.getPersonAccessService().getAllPeople().isEmpty())
            || !hasStarted) {
            initPeople(session);
            System.out.println("Initializing people");
            session.setAttribute("hasStarted", true);
        }

        if (player.getEventsAccessService().getEventList().getEvents().isEmpty()
            || !hasStarted) {
            initEvents(session);
            System.out.println("Initializing events");
            session.setAttribute("hasStarted", true);
        }

        if (player.getStoryFlagAccessService()
                  .getStoryFlagList()
                  .getStoryFlags()
                  .isEmpty() || !hasStarted) {
            initStoryFlags(session);
            System.out.println("Initializing story flags");
            session.setAttribute("hasStarted", true);
        }
    }


    public void initPeople(HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        List<Person> personList = new LinkedList<>();

        personList.add(new Person("Narrator", Gender.MALE, false, null));
        personList.add(new Person("Joe", Gender.MALE, false, null));
        personList.add(new Person("Debbie", Gender.FEMALE, false, null));
        personList.add(new Person("Diane", Gender.FEMALE, false, null));
        personList.add(new Person("Eve", Gender.FEMALE, false, null));

        for (Person person : personList) {
            player.getPersonAccessService().addPerson(person);
        }

        initDialogue(session);
    }

    public void initDialogue(HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        try {
            // NARRATOR
            setPersonDialogue(player, "Narrator",
                              "/dialogues/characters/narrator/dialogue.txt",
                              session);

            // JOE
            // DIANE
            // DEBBIE
            // EVE
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPersonDialogue(Player player, String personName,
                                   String dialogueFilePath, HttpSession session)
            throws IOException {
        InputStream dialogueFile = new ClassPathResource(
                dialogueFilePath).getInputStream();

        DialogueList dialogueList = resourceFileReader.getDialogueListFromFile(
                dialogueFile, session);

        Person person = player.getPersonAccessService().getPersonByName(
                personName);
        person.setDialogueList(dialogueList);
    }

    public void initEvents(HttpSession session) {
        Player player = (Player) session.getAttribute("player");

        try (InputStream eventsFile = new ClassPathResource(
                "/events/events.txt").getInputStream()) {

            player.getEventsAccessService().getEventsAccess().setEventList(
                    resourceFileReader.getEventListFromFile(eventsFile));

        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
        }
    }

    public void initStoryFlags(HttpSession session) {
        Player player = (Player) session.getAttribute("player");

        try (InputStream eventsFile = new ClassPathResource(
                "/events/storyFlags.txt").getInputStream()) {

            player.getStoryFlagAccessService().getFlagAccess().setStoryFlagList(
                    resourceFileReader.getStoryFlagListFromFile(eventsFile));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
