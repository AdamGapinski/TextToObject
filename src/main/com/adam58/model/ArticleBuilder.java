package com.adam58.model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Adam Gapiński
 */
public class ArticleBuilder implements Article.IArticleBuilder {
    private String title;
    private List<String> contentLines = new LinkedList<>();

    public ArticleBuilder(String title) {
        this.title = title;
    }

    public List<String> getContentLines() {
        return contentLines;
    }

    @Override
    public void addContent(String content, boolean inNewLine) {
        content = content.trim();

        if (inNewLine || contentLines.size() == 0) {
            contentLines.add(content);
        } else {

            /*concat to last line*/
            int lastElementIndex = contentLines.size() - 1;
            String concatenatedLine = contentLines.get(lastElementIndex).concat(" ").concat(content);

            contentLines.set(lastElementIndex, concatenatedLine);
        }
    }

    @Override
    public Article createArticle() {
        return new Article(title, contentLines);
    }
}
