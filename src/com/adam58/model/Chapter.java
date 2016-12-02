package com.adam58.model;

import java.util.List;

/**
 * @author Adam Gapiński
 */
public class Chapter {

    public List<String> getContent() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public interface IChapterBuilder {
        boolean addArticle(Article article);
        boolean addArticlesIntroductionContent(String content);
        boolean addIntroductionNewLine();
        Chapter createChapter();
    }
}
