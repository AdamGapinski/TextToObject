package com.adam58.view;

import com.adam58.controller.ArticleRange;

/**
 * @author Adam Gapiński
 */
public interface IDocumentView {
    void printChapter(int chapterNumber);
    void printArticle(int articleNumber);
    void printArticleRange(ArticleRange articleRange);
}
