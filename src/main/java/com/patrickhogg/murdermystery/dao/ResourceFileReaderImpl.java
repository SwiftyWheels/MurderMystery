package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.*;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Patrick Hogg
 */
public class ResourceFileReaderImpl implements ResourceFileReader {

    @Override
    public List<File> getFilesFromPath(String pathName) {
        List<File> fileList = new LinkedList<>();
        File folder = new File(pathName);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileList.add(file);
                    }
                }
            }
        }
        return fileList;
    }

    @Override
    public File getFileFromPath(String pathName, String fileName) {
        for (File file : getFilesFromPath(pathName)) {
            if (file.getName().equalsIgnoreCase(fileName)) {
                return file;
            }
        }
        return null;
    }

    @Override
    public DialogueList getDialogueListFromFile(InputStream file,
                                                HttpSession session) {
        DialogueList dialogueList = new DialogueList();
        Player player = (Player) session.getAttribute("player");
        try {
            if (file.available() > 0) {
                try (BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(file))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] dialogueText = line.split("\\R");
                        for (String text : dialogueText) {
                            Dialogue dialogue = new Dialogue(
                                    dialogueList.getDialogues().size(),
                                    filterInputString(player, text), false);
                            dialogueList.addDialogue(dialogue);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Can't load file! File doesn't exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialogueList;
    }

    @Override
    public EventList getEventListFromFile(InputStream file) {
        EventList eventList = new EventList();
        try {
            if (file.available() > 0) {
                try (BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(file))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] eventText = line.split(",");
                        String actor = eventText[0];
                        String id = eventText[1];
                        String url = eventText[2];
                        Event event = new Event(actor, id, url);
                        eventList.getEvents().add(event);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Can't load file! File doesn't exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventList;
    }

    @Override
    public StoryFlagList getStoryFlagListFromFile(InputStream file) {
        StoryFlagList storyFlagList = new StoryFlagList();
        try {
            if (file.available() > 0) {
                try (BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(file))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] flagText = line.split(",");
                        String flagName = flagText[0];
                        boolean flagValue = Boolean.parseBoolean(flagText[1]);
                        StoryFlag storyFlag = new StoryFlag(flagName,
                                                            flagValue);
                        storyFlagList.getStoryFlags().add(storyFlag);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Can't load file! File doesn't exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storyFlagList;
    }

    @Override
    public String replaceTemplateString(String pattern, String text,
                                        String replacement) {
        Pattern p = Pattern.compile(pattern);
        return p.matcher(text).replaceAll(
                m -> m.group().replaceAll(pattern, replacement));
    }

    @Override
    public String filterInputString(Player player, String input) {
        String nameReplace = replaceTemplateString("\\[[insert]+ [name]+]",
                                                   input, player.getName());

        String ageReplace = replaceTemplateString("\\[[insert]+ [age]+]",
                                                  nameReplace, String.valueOf(
                        player.getAge()));

        return replaceTemplateString("\\[[insert]+ [profession]+]", ageReplace,
                                     String.valueOf(player.getProfession()));
    }
}
