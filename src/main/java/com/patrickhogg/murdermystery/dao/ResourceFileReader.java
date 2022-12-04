package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.DialogueList;
import com.patrickhogg.murdermystery.model.EventList;
import com.patrickhogg.murdermystery.model.Player;
import com.patrickhogg.murdermystery.model.StoryFlagList;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author Patrick Hogg
 */
public interface ResourceFileReader {

    List<File> getFilesFromPath(String pathName);

    File getFileFromPath(String pathName, String fileName);

    DialogueList getDialogueListFromFile(InputStream file, HttpSession session);

    EventList getEventListFromFile(InputStream file);

    StoryFlagList getStoryFlagListFromFile(InputStream file);

    String replaceTemplateString(String pattern, String text,
                                 String replacement);

    String filterInputString(Player player, String input);
}
