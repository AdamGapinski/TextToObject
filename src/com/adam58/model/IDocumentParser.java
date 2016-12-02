package com.adam58.model;

import java.io.File;
import java.util.List;

/**
 * @author Adam Gapiński
 */
public interface IDocumentParser {
    boolean parseDocument(File document);
    List<Article> getArticles();
    List<Chapter> getChapters();
}
