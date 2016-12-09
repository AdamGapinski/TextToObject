package com.adam58.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Adam Gapiński
 */
public class ChapterBuilder implements Chapter.IChapterBuilder {
    private String title;
    private Introduction lastIntroduction = new Introduction();
    private boolean addingIntroduction = false;
    private Map<Article, Introduction> articleIntroductionMap = new LinkedHashMap<>();

    public ChapterBuilder(String title) {
        this.title = title;
    }

    @Override
    public Chapter createChapter() {
        return new Chapter(articleIntroductionMap, title);
    }

    @Override
    public boolean addArticle(Article article) {
        addingIntroduction = false;

        return articleIntroductionMap.put(article, lastIntroduction) == null;
    }

    @Override
    public boolean addArticlesIntroductionContent(String content) {
        if (addingIntroduction) {
            return lastIntroduction.addContent(content);
        } else {
            lastIntroduction = new Introduction();
            addingIntroduction = true;
            return lastIntroduction.addContent(content);
        }
    }

}
