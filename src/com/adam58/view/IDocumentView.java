package com.adam58.view;

/**
 * @author Adam Gapiński
 */
public interface IDocumentView {
    void printChapter(int chapterNumber);
    void printArticle(int articleNumber);
    void printArticleRange(int firstArticleNumber, int lastArticleNumber);
}
