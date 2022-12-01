package com.patrickhogg.murdermystery.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class Dialogue implements Serializable {
    private int id;
    private String text;
    private boolean isRead;

    public Dialogue(int id, String text, boolean isRead) {
        this.id = id;
        this.text = text;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dialogue dialogue)) {
            return false;
        }
        return getId() == dialogue.getId() && isRead() == dialogue.isRead()
               && Objects.equals(getText(), dialogue.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), isRead());
    }

    @Override
    public String toString() {
        return "Dialogue{" + "id=" + id + ", text='" + text + '\'' + ", isRead="
               + isRead + '}';
    }
}
