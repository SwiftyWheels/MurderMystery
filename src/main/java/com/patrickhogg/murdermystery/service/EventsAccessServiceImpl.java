package com.patrickhogg.murdermystery.service;

import com.patrickhogg.murdermystery.dao.EventsAccessImpl;
import com.patrickhogg.murdermystery.model.Event;
import com.patrickhogg.murdermystery.model.EventList;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class EventsAccessServiceImpl implements EventsAccessService,
                                                Serializable {

    private final EventsAccessImpl eventsAccess = new EventsAccessImpl();

    public EventsAccessServiceImpl() {
        // empty no args constructor
    }

    public EventsAccessImpl getEventsAccess() {
        return eventsAccess;
    }

    @Override
    public EventList getEventList() {
        return getEventsAccess().getEventList();
    }

    @Override
    public Event getEvent(Event event) {
        return getEventsAccess().getEvent(event);
    }

    @Override
    public Event getEventByActorAndID(String actor, String id) {
        List<Event> eventList = getEventsByActor(actor);

        for (Event event : eventList) {
            if (event.getId().equalsIgnoreCase(id)) {
                return event;
            }
        }
        return null;
    }

    @Override
    public List<Event> getEventsByActor(String name) {
        return getEventsAccess().getEventsByActor(name);
    }

    @Override
    public List<Event> getEventsByID(String id) {
        return getEventsAccess().getEventsByID(id);
    }

    @Override
    public List<Event> getEventsByURL(String url) {
        return getEventsAccess().getEventsByURL(url);
    }

    @Override
    public void addEvent(Event event) {
        getEventsAccess().addEvent(event);
    }

    @Override
    public void removeEvent(Event event) {
        getEventsAccess().removeEvent(event);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventsAccessServiceImpl that)) {
            return false;
        }
        return Objects.equals(getEventsAccess(), that.getEventsAccess());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventsAccess());
    }

    @Override
    public String toString() {
        return "EventsAccessServiceImpl{" + "eventsAccess=" + eventsAccess
               + '}';
    }
}
