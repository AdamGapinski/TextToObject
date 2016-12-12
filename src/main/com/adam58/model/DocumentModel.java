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

        if (chapterNumber < 1 || chapterNumber >= chapters.size()) {
            throw new NoSuchDocumentElementException("Chapter " + chapterNumber + ". not exists");
        }

        return chapters.get(chapterNumber - 1);
    }

    @Override
    public Article getArticle(int articleNumber) throws NoSuchDocumentElementException{

        if (articleNumber < 1 || articleNumber > articles.size()) {
            throw new NoSuchDocumentElementException("Article " + articleNumber + ". not exists");
        }

        return articles.get(articleNumber - 1);
    }

    public static class NoSuchDocumentElementException extends Exception {
        public NoSuchDocumentElementException(String message) {
            super(message);
        }
    }
}
