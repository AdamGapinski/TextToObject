package com.adam58.model;

import java.nio.file.Path;

/**
 * @author Adam Gapiński
 */
public interface IDocumentModel {
    void loadDocument(Path documentFilePath);
    Chapter getChapter(int chapterNumber);
    Article getArticle(int articleNumber);
}
