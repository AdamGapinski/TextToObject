package com.adam58.model;

import java.util.List;

/**
 * @author Adam Gapiński
 */
public class Article {
    private String title;
    private List<String> contentLines;

    public Article(String title, List<String> contentLines) {
        this.title = title;
        this.contentLines = contentLines;
    }

    public List<String> getContentLines() {
        return contentLines;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (contentLines != null ? contentLines.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        return contentLines != null ? contentLines.equals(article.contentLines) : article.contentLines == null;
    }

    public interface IArticleBuilder {
        void addContent(String content, boolean inNewLine);
        Article createArticle();
    }
}
