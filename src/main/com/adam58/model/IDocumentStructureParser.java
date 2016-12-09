package com.adam58.model;

/**
 * @author Adam Gapiński
 */
public interface IDocumentStructureParser {
    boolean isNextArticle(String line);
    boolean isEndOfArticle(String line);
    boolean isNextChapter(String line);
    boolean isNextArticlesIntroduction(String line);
    boolean isEndOfChapter(String line);
    boolean isRedundantLine(String line);
    boolean isInNewLine(String line);
    boolean isConveyanceEnding(String line);
}
