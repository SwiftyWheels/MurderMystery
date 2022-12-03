package com.patrickhogg.murdermystery.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class Event implements Serializable {
    private String actor;
    private String id;
    private String url;

    public Event(String actor, String id, String url) {
        this.actor = actor;
        this.id = id;
        this.url = url;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event event)) {
            return false;
        }
        return Objects.equals(getActor(), event.getActor()) && Objects.equals(
                getId(), event.getId()) && Objects.equals(getUrl(),
                                                          event.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActor(), getId(), getUrl());
    }

    @Override
    public String toString() {
        return "Event{" + "actor='" + actor + '\'' + ", id='" + id + '\''
               + ", url='" + url + '\'' + '}';
    }
}
