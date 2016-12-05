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
    public Chapter getChapter(int chapterNumber) {
        return chapters.get(chapterNumber);
    }

    @Override
    public Article getArticle(int articleNumber) {
        return articles.get(articleNumber);
    }
}
