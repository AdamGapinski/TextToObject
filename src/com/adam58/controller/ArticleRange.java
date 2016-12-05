package com.adam58.controller;

/**
 * @author Adam Gapiński
 */
public class ArticleRange {
    private final int firstArticleNumber;
    private final int lastArticleNumber;

    public ArticleRange(int firstArticleNumber, int lastArticleNumber) {
        this.firstArticleNumber = firstArticleNumber;
        this.lastArticleNumber = lastArticleNumber;
    }

    public int getFirstArticleNumber() {
        return firstArticleNumber;
    }

    public int getLastArticleNumber() {
        return lastArticleNumber;
    }
}
