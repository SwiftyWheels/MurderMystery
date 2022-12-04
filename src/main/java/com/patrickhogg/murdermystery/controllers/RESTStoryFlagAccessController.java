package com.patrickhogg.murdermystery.controllers;

import com.patrickhogg.murdermystery.model.Player;
import com.patrickhogg.murdermystery.model.StoryFlagList;
import com.patrickhogg.murdermystery.service.StoryFlagAccessServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author Patrick Hogg
 */
@RestController
@RequestMapping("/api/storyFlag")
public class RESTStoryFlagAccessController {

    @GetMapping("/getStoryFlags")
    @ResponseBody
    public StoryFlagList getStoryFlags(HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        StoryFlagAccessServiceImpl flagAccess
                = player.getStoryFlagAccessService();
        return flagAccess.getStoryFlagList();
    }

    @GetMapping("/getStoryFlagValue/{flagName}")
    public boolean getStoryFlagValue(@PathVariable String flagName,
                                     HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        StoryFlagAccessServiceImpl flagAccess
                = player.getStoryFlagAccessService();
        boolean flagValue = flagAccess.getStoryFlagValue(flagName);
        System.out.println(flagName + ":" + flagValue);
        return flagValue;
    }

    @GetMapping("/setStoryFlagValue/{flagName}/{value}")
    public void setStoryFlagValue(@PathVariable String flagName,
                                  @PathVariable String value,
                                  HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        StoryFlagAccessServiceImpl flagAccess
                = player.getStoryFlagAccessService();
        boolean flagValue = Boolean.parseBoolean(value);

        flagAccess.setStoryFlagValue(flagName, flagValue);
        System.out.println("Setting flag: " + flagName + " : " + flagValue);
    }
}
