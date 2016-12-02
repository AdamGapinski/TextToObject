package com.adam58.model;

/**
 * @author Adam Gapiński
 */
public class Article {

    @Override
    public String toString() {
        return super.toString();
    }

    public interface IArticleBuilder {
        void setTitle(String title);
        void addContent(String content);
        void addNewLine();
        Article createArticle();
    }
}
