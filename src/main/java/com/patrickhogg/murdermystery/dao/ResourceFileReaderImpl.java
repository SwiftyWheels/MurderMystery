package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.*;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Patrick Hogg
 */
public class ResourceFileReaderImpl implements ResourceFileReader {

    @Override
    public List<File> getFilesFromPath(String pathName) {
        List<File> fileList = new LinkedList<>();
        File folder = new File(pathName);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles(File::isFile);
            if (files != null) {
                fileList.addAll(Arrays.asList(files));
            }
        }
        return fileList;
    }

    @Override
    public File getFileFromPath(String pathName, String fileName) {
        List<File> files = getFilesFromPath(pathName);
        return files.stream()
                    .filter(file -> file.getName().equalsIgnoreCase(fileName))
                    .findFirst()
                    .orElse(null);
    }

    @Override
    public DialogueList getDialogueListFromFile(InputStream file,
                                                HttpSession session) {
        DialogueList dialogueList = new DialogueList();
        Player player = (Player) session.getAttribute("player");
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
        return dialogueList;
    }

    @Override
    public EventList getEventListFromFile(InputStream file) {
        EventList eventList = new EventList();
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
            System.err.println("Error: " + e.getMessage());
        }

        return eventList;
    }

    @Override
    public StoryFlagList getStoryFlagListFromFile(InputStream file) {
        StoryFlagList storyFlagList = new StoryFlagList();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] flagText = line.split(",");
                String flagName = flagText[0];
                boolean flagValue = Boolean.parseBoolean(flagText[1]);
                StoryFlag storyFlag = new StoryFlag(flagName, flagValue);
                storyFlagList.getStoryFlags().add(storyFlag);
            }
        } catch (IOException e) {
            System.err.println("Can't load file! File doesn't exist!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storyFlagList;
    }

    @Override
    public String replaceTemplateString(String text, String pattern,
                                        String replacement) {
        return text.replaceAll(pattern, replacement);
    }

    @Override
    public String filterInputString(Player player, String input) {
        String nameReplace = replaceTemplateString(input,
                                                   "\\[[insert]+ [name]+]",
                                                   player.getName());
        String ageReplace = replaceTemplateString(nameReplace,
                                                  "\\[[insert]+ [age]+]",
                                                  String.valueOf(
                                                          player.getAge()));
        return replaceTemplateString(ageReplace, "\\[[insert]+ [profession]+]",
                                     String.valueOf(player.getProfession()));
    }
}
