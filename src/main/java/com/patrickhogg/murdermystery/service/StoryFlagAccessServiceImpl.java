package com.patrickhogg.murdermystery.service;

import com.patrickhogg.murdermystery.dao.StoryFlagAccessImpl;
import com.patrickhogg.murdermystery.model.StoryFlag;
import com.patrickhogg.murdermystery.model.StoryFlagList;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class StoryFlagAccessServiceImpl implements StoryFlagAccessService,
                                                   Serializable {

    private final StoryFlagAccessImpl flagAccess = new StoryFlagAccessImpl();

    public StoryFlagAccessImpl getFlagAccess() {
        return flagAccess;
    }

    @Override
    public StoryFlag getStoryFlag(StoryFlag storyFlag) {
        if (storyFlag != null) {
            return flagAccess.getStoryFlag(storyFlag);
        }
        return null;
    }

    @Override
    public StoryFlag getStoryFlag(String flagName) {
        return flagAccess.getStoryFlag(flagName);
    }

    @Override
    public StoryFlagList getStoryFlagList() {
        return flagAccess.getStoryFlagList();
    }

    @Override
    public boolean getStoryFlagValue(StoryFlag storyFlag) {
        if (storyFlag != null) {
            return flagAccess.getStoryFlagValue(storyFlag);
        }
        return false;
    }

    @Override
    public boolean getStoryFlagValue(String flagName) {
        return flagAccess.getStoryFlagValue(flagName);
    }


    @Override
    public void setStoryFlagValue(StoryFlag storyFlag, boolean value) {
        flagAccess.setStoryFlagValue(storyFlag, value);
    }

    @Override
    public void setStoryFlagValue(String flagName, boolean value) {
        flagAccess.setStoryFlagValue(flagName, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StoryFlagAccessServiceImpl that)) {
            return false;
        }
        return Objects.equals(getFlagAccess(), that.getFlagAccess());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlagAccess());
    }

    @Override
    public String toString() {
        return "StoryFlagAccessServiceImpl{" + "flagAccess=" + flagAccess + '}';
    }
}
