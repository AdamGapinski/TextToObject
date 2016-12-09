package com.adam58.model;

import java.util.Map;

/**
 * @author Adam Gapiński
 */
public class Chapter {
    private Map<Article, Introduction> articleIntroductionMap;
    private String title;

    public Chapter(Map<Article, Introduction> articleIntroductionMap, String title) {
        this.articleIntroductionMap = articleIntroductionMap;
        this.title = title;
    }

    public Map<Article, Introduction> getArticleIntroductionMap() {
        return articleIntroductionMap;
    }


    @Override
    public String toString() {
        return title;
    }

    public interface IChapterBuilder {
        boolean addArticle(Article article);
        boolean addArticlesIntroductionContent(String content);
        Chapter createChapter();
    }
}
