package com.patrickhogg.murdermystery.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class StoryFlag implements Serializable {
    String flagName;
    boolean isActivated;

    public StoryFlag(String flagName, boolean flagValue) {
        this.flagName = flagName;
        this.isActivated = flagValue;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        this.isActivated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StoryFlag storyFlag)) {
            return false;
        }
        return isActivated() == storyFlag.isActivated() && Objects.equals(
                getFlagName(), storyFlag.getFlagName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlagName(), isActivated());
    }

    @Override
    public String toString() {
        return "StoryFlag{" + "flagName='" + flagName + '\'' + ", flagValue="
               + isActivated + '}';
    }
}
