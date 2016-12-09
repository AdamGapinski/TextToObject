package com.adam58.model;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Adam Gapiński
 */
public class DocumentModel implements IDocumentModel {
    private IDocumentParser parser = new DocumentParser();
    private List<Article> articles;
    private List<Chapter> chapters;

    @Override
    public void loadDocument(Path documentFilePath) throws DocumentNotFoundException {
        parser.parseDocument(documentFilePath);

        articles = parser.getArticles();
        chapters = parser.getChapters();
    }

    @Override
    public Chapter getChapter(int chapterNumber) throws NoSuchDocumentElementException{
        chapterNumber -= 1;

        if (chapterNumber < 0 || chapterNumber >= chapters.size()) {
            throw new NoSuchDocumentElementException("Chapter " + chapterNumber + ". not exists");
        }

        return chapters.get(chapterNumber);
    }

    @Override
    public Article getArticle(int articleNumber) throws NoSuchDocumentElementException{
        articleNumber -= 1;

        if (articleNumber < 0 || articleNumber >= articles.size()) {
            throw new NoSuchDocumentElementException("Article " + articleNumber + ". not exists");
        }

        return articles.get(articleNumber);
    }

    public static class NoSuchDocumentElementException extends Exception {
        public NoSuchDocumentElementException(String message) {
            super(message);
        }
    }
}
