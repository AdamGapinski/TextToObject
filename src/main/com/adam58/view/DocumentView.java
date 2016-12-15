package com.adam58.view;

import com.adam58.controller.ArticleRange;
import com.adam58.model.*;

import java.util.Map;

/**
 * @author Adam Gapiński
 */
public class DocumentView implements IDocumentView {
    private final int CONSOLE_WIDTH = 110;
    private IDocumentModel documentModel;
    private final int MIN_LINE_LENGTH = 18;

    public DocumentView(IDocumentModel documentModel) {
        this.documentModel = documentModel;
    }

    @Override
    public void printChapter(int chapterNumber) {
        try {

            Chapter chapter = documentModel.getChapter(chapterNumber);
            System.out.println(chapter);

            Map<Article, Introduction> articleIntroductionMap = chapter.getArticleIntroductionMap();

            Introduction introduction = null;
            for (Map.Entry<Article, Introduction> entry : articleIntroductionMap.entrySet()) {
                Article article = entry.getKey();

                if (introduction == null || articleIntroductionMap.get(article) != introduction) {
                    introduction = articleIntroductionMap.get(article);

                    introduction.getContent().forEach(this::printLineToConsole);
                }

                printLineToConsole(article.toString());
                article.getContentLines().forEach(this::printLineToConsole);

                System.out.println();
            }

        } catch (DocumentModel.NoSuchDocumentElementException e) {
            printLineToConsole(e.getMessage());
        }
    }

    @Override
    public void printArticle(int articleNumber) {
        try {
            Article article = documentModel.getArticle(articleNumber);

            printLineToConsole(article);
            article.getContentLines().forEach(this::printLineToConsole);

        } catch (DocumentModel.NoSuchDocumentElementException e) {
            printLineToConsole(e.getMessage());
        }
    }

    @Override
    public void printArticleRange(ArticleRange articleRange) {
            for (int i = articleRange.firstArticleNumber; i <= articleRange.lastArticleNumber; ++i) {
                this.printArticle(i);
                System.out.println();
            }
    }

    private void printLineToConsole(Object object) {
        String line = object.toString().trim();

        int lastIndex = line.lastIndexOf(" ", CONSOLE_WIDTH);

        while (line.length() - CONSOLE_WIDTH > MIN_LINE_LENGTH && lastIndex != -1) {

            System.out.println(line.substring(0, lastIndex));

            if (lastIndex + 1 < line.length()) {
                line = line.substring(lastIndex + 1).trim();
            }

            lastIndex = line.lastIndexOf(" ", CONSOLE_WIDTH);
        }

        if (line.length() != 0) {
            System.out.println(line);
        }

    }


}
