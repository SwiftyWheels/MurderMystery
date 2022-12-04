package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.StoryFlag;
import com.patrickhogg.murdermystery.model.StoryFlagList;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class StoryFlagAccessImpl implements StoryFlagsAccess, Serializable {

    private StoryFlagList storyFlagList = new StoryFlagList();

    public void setStoryFlagList(StoryFlagList storyFlagList) {
        this.storyFlagList = storyFlagList;
    }

    @Override
    public StoryFlagList getStoryFlagList() {
        return storyFlagList;
    }

    @Override
    public StoryFlag getStoryFlag(StoryFlag storyFlag) {
        if (getStoryFlagList().getStoryFlags().contains(storyFlag)) {
            for (StoryFlag flag : getStoryFlagList().getStoryFlags()) {
                if (flag.equals(storyFlag)) {
                    return flag;
                }
            }
        }
        return null;
    }

    @Override
    public StoryFlag getStoryFlag(String flagName) {
        for (StoryFlag flag : getStoryFlagList().getStoryFlags()) {
            if (flag.getFlagName().equalsIgnoreCase(flagName)) {
                return flag;
            }
        }
        return null;
    }

    @Override
    public boolean getStoryFlagValue(StoryFlag storyFlag) {
        StoryFlag flag = getStoryFlag(storyFlag);
        return flag.isActivated();
    }

    @Override
    public boolean getStoryFlagValue(String flagName) {
        StoryFlag flag = getStoryFlag(flagName);
        return flag.isActivated();
    }

    @Override
    public void setStoryFlagValue(StoryFlag storyFlag, boolean value) {
        StoryFlag flag = getStoryFlag(storyFlag);
        flag.setActivated(value);
    }

    @Override
    public void setStoryFlagValue(String flagName, boolean value) {
        StoryFlag flag = getStoryFlag(flagName);
        flag.setActivated(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StoryFlagAccessImpl that)) {
            return false;
        }
        return Objects.equals(getStoryFlagList(), that.getStoryFlagList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoryFlagList());
    }

    @Override
    public String toString() {
        return "StoryFlagAccessImpl{" + "storyFlagList=" + storyFlagList + '}';
    }
}
