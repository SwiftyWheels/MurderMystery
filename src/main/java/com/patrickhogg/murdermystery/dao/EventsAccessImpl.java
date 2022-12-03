package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.Event;
import com.patrickhogg.murdermystery.model.EventList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class EventsAccessImpl implements EventsAccess {

    private EventList eventList = new EventList();

    @Override
    public EventList getEventList() {
        return eventList;
    }

    public void setEventList(EventList eventList) {
        this.eventList = eventList;
    }

    @Override
    public Event getEvent(Event event) {
        if (event != null && getEventList().getEvents().contains(event)) {
            for (Event currentEvent : getEventList().getEvents()) {
                if (currentEvent.equals(event)) {
                    return currentEvent;
                }
            }
        }
        return null;
    }

    @Override
    public List<Event> getEventsByActor(String name) {
        List<Event> events = new LinkedList<>();
        for (Event event : getEventList().getEvents()) {
            if (event.getActor().equalsIgnoreCase(name)) {
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public List<Event> getEventsByID(String id) {
        List<Event> events = new LinkedList<>();
        for (Event event : getEventList().getEvents()) {
            if (event.getId().equalsIgnoreCase(id)) {
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public List<Event> getEventsByURL(String url) {
        List<Event> events = new LinkedList<>();
        for (Event event : getEventList().getEvents()) {
            if (event.getUrl().equalsIgnoreCase(url)) {
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public void addEvent(Event event) {
        if (event != null && !getEventList().getEvents().contains(event)) {
            getEventList().getEvents().add(event);
        }
    }

    @Override
    public void removeEvent(Event event) {
        if (event != null) {
            getEventList().getEvents().remove(event);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventsAccessImpl that)) {
            return false;
        }
        return Objects.equals(getEventList(), that.getEventList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventList());
    }

    @Override
    public String toString() {
        return "EventsAccessImpl{" + "eventList=" + eventList + '}';
    }
}

