package com.adam58.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam Gapiński
 */
public class Introduction {
    List<String> content = new ArrayList<>();

    public boolean addContent(String content) {
        return this.content.add(content);
    }

    public List<String> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Introduction that = (Introduction) o;

        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }
}
