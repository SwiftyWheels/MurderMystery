package com.patrickhogg.murdermystery.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class Note implements Serializable {
    private String notes;

    public Note() {
        // no args constructor
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Note notes1)) {
            return false;
        }
        return Objects.equals(getNotes(), notes1.getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNotes());
    }

    @Override
    public String toString() {
        return "Notes{" + "notes='" + notes + '\'' + '}';
    }
}
