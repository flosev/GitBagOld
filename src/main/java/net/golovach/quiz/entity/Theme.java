package net.golovach.quiz.entity;

import java.util.ArrayList;
import java.util.List;

public class Theme {
    private final String id;
    private String caption;
    private Theme parent;
    private List<Theme> children;

    public Theme(String id, String caption, Theme parent, List<Theme> children) {
        this.id = id;
        this.caption = caption;
        this.parent = parent;
        this.children = new ArrayList<>(children);
    }

    public String getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Theme getParent() {
        return parent;
    }

    public void setParent(Theme parent) {
        this.parent = parent;
    }

    public List<Theme> getChildren() {
        return new ArrayList<>(children);
    }

    public void setChildren(List<Theme> children) {
        if (children == null) {
            throw new IllegalArgumentException();
        }
        this.children = new ArrayList<>(children);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Theme that = (Theme) o;
        // todo: only id or all fields?
        return !(id != null ? !id.equals(that.id) : that.id != null);
    }

    @Override
    public int hashCode() {
        // todo: only id or all fields?
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id='" + id + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }
}
