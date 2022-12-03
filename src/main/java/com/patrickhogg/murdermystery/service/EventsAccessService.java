package com.patrickhogg.murdermystery.service;

import com.patrickhogg.murdermystery.model.Event;
import com.patrickhogg.murdermystery.model.EventList;

import java.util.List;

/**
 * @author Patrick Hogg
 */
public interface EventsAccessService {
    EventList getEventList();

    Event getEvent(Event event);

    Event getEventByActorAndID(String actor, String id);

    List<Event> getEventsByActor(String name);
    List<Event> getEventsByID(String id);
    List<Event> getEventsByURL(String url);

    void addEvent(Event event);

    void removeEvent(Event event);
}
