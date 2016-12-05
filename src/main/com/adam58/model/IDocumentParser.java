package com.adam58.model;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Adam Gapiński
 */
public interface IDocumentParser {
    boolean parseDocument(Path document) throws DocumentNotFoundException;
    List<Article> getArticles();
    List<Chapter> getChapters();
}
