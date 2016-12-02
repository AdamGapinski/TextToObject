package com.adam58.model;

/**
 * @author Adam Gapiński
 */
public interface IDocumentStructureParser {
    boolean isNextArticle(String line);
    boolean isEndOfArticle(String line);
    boolean isNextChapter(String line);
    boolean isNextArticlesIntroduction(String line);
    boolean isRedundantLine(String line);
    boolean isNewLine(String line);
    boolean isConveyedToNewLine(String line);
    String concatConveyed(String firstLine, String secondLine);
}
