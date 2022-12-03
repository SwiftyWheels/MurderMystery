package com.patrickhogg.murdermystery.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class EventList implements Serializable {

    private List<Event> events = new LinkedList<>();

    public EventList() {
        // empty no args constructor
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventList eventList1)) {
            return false;
        }
        return Objects.equals(getEvents(), eventList1.getEvents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEvents());
    }

    @Override
    public String toString() {
        return "EventList{" + "eventList=" + events + '}';
    }
}
