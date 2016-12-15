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
}
