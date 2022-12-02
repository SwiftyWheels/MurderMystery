package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.DialogueList;
import com.patrickhogg.murdermystery.model.Player;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * @author Patrick Hogg
 */
public interface ResourceFileReader {

    List<File> getFilesFromPath(String pathName);

    File getFileFromPath(String pathName, String fileName);

    DialogueList getDialogueListFromFile(File file, HttpSession session);

    String replaceTemplateString(String pattern, String text,
                                 String replacement);

    String filterInputString(Player player, String input);
}
