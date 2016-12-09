package com.adam58.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Adam Gapiński
 */
public class DocumentParser implements IDocumentParser {
    private IDocumentStructureParser structureParser = new DocumentStructureParser();
    private List<Article> articles = new ArrayList<>();
    private List<Chapter> chapters = new ArrayList<>();

    private Article.IArticleBuilder articleBuilder;;
    private Chapter.IChapterBuilder chapterBuilder;

    @Override
    public void parseDocument(Path document) throws DocumentNotFoundException {
        try (Scanner scanner = new Scanner(new FileReader(new File(document.toString())))) {

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                endDocumentElement(line);
                boolean isTitleLine = startDocumentElement(line);

                if (!isTitleLine && !structureParser.isRedundantLine(line)) {
                    saveLine(line);
                }
            }

            Article article = articleBuilder.createArticle();
            articles.add(article);
            chapterBuilder.addArticle(article);
            chapters.add(chapterBuilder.createChapter());

        } catch (FileNotFoundException e) {
            throw new DocumentNotFoundException(document.toString() + " file not found");
        }

    }

    private void endDocumentElement(String line) {
        if (structureParser.isEndOfChapter(line)) {
            chapters.add(chapterBuilder.createChapter());

        }
        if (structureParser.isEndOfArticle(line)) {
            Article article = articleBuilder.createArticle();
            articles.add(article);
            chapterBuilder.addArticle(article);

        }
    }

    private boolean startDocumentElement(String line) {
        if (structureParser.isNextArticle(line)) {
            articleBuilder = new ArticleBuilder(line);
            return true;

        } else if (structureParser.isNextChapter(line)) {
            chapterBuilder = new ChapterBuilder(line);
            return true;
        }

        return false;
    }

    private void saveLine(String line) {
        if (structureParser.isNextArticlesIntroduction(line)) {
            chapterBuilder.addArticlesIntroductionContent(line);

        } else if (structureParser.isConveyanceEnding(line)) {

            line = line.substring(0, line.length() - 1);
            articleBuilder.addContent(line, structureParser.isInNewLine(line));

        } else {
            articleBuilder.addContent(line + " ", structureParser.isInNewLine(line));
        }
    }

    @Override
    public List<Article> getArticles() {
        return articles;
    }

    @Override
    public List<Chapter> getChapters() {
        return chapters;
    }

}

