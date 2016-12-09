package com.adam58.model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Adam Gapiński
 */
public class ArticleBuilder implements Article.IArticleBuilder {
    private String title;
    private List<String> contentLines = new LinkedList<>();
    private boolean concatToLast = false;

    public ArticleBuilder(String title) {
        this.title = title;
    }

    public List<String> getContentLines() {
        return contentLines;
    }

    @Override
    public void addContent(String content, boolean inNewLine) {

        if (inNewLine || contentLines.size() == 0) {
            contentLines.add(content);
        } else {

        /*concat to last line*/
        int lastIndex = contentLines.size() - 1;

        String newLine = contentLines.get(lastIndex);
        newLine = newLine.concat(content);

        contentLines.remove(lastIndex);
        contentLines.add(newLine);
        }
    }

    @Override
    public Article createArticle() {
        return new Article(title, contentLines);
    }
}
