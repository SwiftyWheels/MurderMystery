package com.patrickhogg.murdermystery.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class StoryFlagList implements Serializable {
    private List<StoryFlag> storyFlags = new LinkedList<>();

    public StoryFlagList() {
        // empty no args constructor
    }

    public List<StoryFlag> getStoryFlags() {
        return storyFlags;
    }

    public void setStoryFlags(List<StoryFlag> storyFlags) {
        this.storyFlags = storyFlags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StoryFlagList that)) {
            return false;
        }
        return Objects.equals(getStoryFlags(), that.getStoryFlags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoryFlags());
    }

    @Override
    public String toString() {
        return "StoryFlagList{" + "storyFlags=" + storyFlags + '}';
    }
}
