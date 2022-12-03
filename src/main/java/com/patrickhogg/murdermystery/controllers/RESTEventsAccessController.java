package com.patrickhogg.murdermystery.controllers;

import com.patrickhogg.murdermystery.model.Event;
import com.patrickhogg.murdermystery.model.Player;
import com.patrickhogg.murdermystery.service.EventsAccessServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author Patrick Hogg
 */
@RestController
@RequestMapping("/api/events")
public class RESTEventsAccessController {

    @GetMapping("/checkForEvent/{speaker}/{id}")
    @ResponseBody
    public Event checkForEvent(@PathVariable String speaker,
                               @PathVariable String id, HttpSession session) {
        Player player = (Player) session.getAttribute("player");
        EventsAccessServiceImpl accessService = player.getEventsAccessService();

        Event event = accessService.getEventByActorAndID(speaker, id);
        System.out.println(event);
        return event;
    }
}
