package com.adam58.model;

/**
 * @author Adam Gapiński
 */
public class DocumentStructureParser implements IDocumentStructureParser {
    private boolean article = false;
    private boolean chapter = false;

    @Override
    public boolean isNextArticle(String line) {
        if (line.startsWith("Art. ")) {
            article = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean isEndOfArticle(String line) {
        if ((line.startsWith("Art. ") || line.startsWith("Rozdział ")) && article) {
            article = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean isNextChapter(String line) {
        if (line.startsWith("Rozdział ")) {
            chapter = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean isNextArticlesIntroduction(String line) {
        return chapter && line.equals(line.toUpperCase());
    }

    @Override
    public boolean isEndOfChapter(String line) {
        if (line.startsWith("Rozdział ") && chapter) {
            chapter = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean isRedundantLine(String line) {
        return (!chapter && !article)
                || (line.substring(1,line.length()).equals("Kancelaria Sejmu")
                    || line.equals("2009-11-16"));
    }

    @Override
    public boolean isInNewLine(String line) {
        String[] tokens = line.split(" ");

        return tokens.length != 0 && (Character.isDigit(tokens[0].charAt(0))) && tokens[0].endsWith(".");
    }

    @Override
    public boolean isConveyanceEnding(String line) {
        return line.endsWith("-");
    }
}
