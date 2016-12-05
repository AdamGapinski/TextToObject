package com.adam58.model;

import java.io.FileNotFoundException;
import java.nio.file.Path;

/**
 * @author Adam Gapiński
 */
public interface IDocumentModel {
    void loadDocument(Path documentFilePath) throws FileNotFoundException;
    Chapter getChapter(int chapterNumber);
    Article getArticle(int articleNumber);
}
