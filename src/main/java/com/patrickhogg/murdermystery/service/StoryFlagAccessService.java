package com.patrickhogg.murdermystery.service;

import com.patrickhogg.murdermystery.model.StoryFlag;
import com.patrickhogg.murdermystery.model.StoryFlagList;

/**
 * @author Patrick Hogg
 */
public interface StoryFlagAccessService {
    StoryFlag getStoryFlag(StoryFlag storyFlag);

    StoryFlag getStoryFlag(String flagName);

    StoryFlagList getStoryFlagList();

    boolean getStoryFlagValue(StoryFlag storyFlag);
    boolean getStoryFlagValue(String flagName);

    void setStoryFlagValue(StoryFlag storyFlag, boolean value);

    void setStoryFlagValue(String flagName, boolean value);
}
