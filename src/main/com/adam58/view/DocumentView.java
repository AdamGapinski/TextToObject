package com.adam58.view;

import com.adam58.controller.ArticleRange;
import com.adam58.model.Article;
import com.adam58.model.DocumentModel;
import com.adam58.model.IDocumentModel;
import com.adam58.model.Introduction;

import java.util.Map;

/**
 * @author Adam Gapiński
 */
public class DocumentView implements IDocumentView {
    private final int MAX_LINE_LENGTH = 130;
    private IDocumentModel documentModel;

    public DocumentView(IDocumentModel documentModel) {
        this.documentModel = documentModel;
    }

    @Override
    public void printChapter(int chapterNumber) {
        try {

            Map<Article, Introduction> articleIntroductionMap = documentModel
                    .getChapter(chapterNumber)
                    .getArticleIntroductionMap();

            Introduction introduction = null;
            for (Map.Entry<Article, Introduction> entry : articleIntroductionMap.entrySet()) {
                Article article = entry.getKey();

                if (introduction == null || articleIntroductionMap.get(article) != introduction) {
                    introduction = articleIntroductionMap.get(article);

                    introduction.getContent().forEach(this::printLine);
                }

                printLine(article.toString());
                article.getContentLines().forEach(this::printLine);

                System.out.println();
            }

        } catch (DocumentModel.NoSuchDocumentElementException e) {
            printLine(e.getMessage());
        }
    }

    @Override
    public void printArticle(int articleNumber) {
        try {
            Article article = documentModel.getArticle(articleNumber);

            printLine(article);
            article.getContentLines().forEach(this::printLine);

        } catch (DocumentModel.NoSuchDocumentElementException e) {
            printLine(e.getMessage());
        }
    }

    @Override
    public void printArticleRange(ArticleRange articleRange) {
            for (int i = articleRange.firstArticleNumber; i <= articleRange.lastArticleNumber; ++i) {
                this.printArticle(i);
                System.out.println();
            }
    }

    private void printLine(Object object) {
        String line = object.toString();

        int lastIndex = line.lastIndexOf(" ", MAX_LINE_LENGTH);

        while (line.length() > MAX_LINE_LENGTH && lastIndex != -1) {

            System.out.println(line.substring(0, lastIndex));
            line = line.trim();

            if (lastIndex + 1 < line.length()) {
                line = line.substring(lastIndex + 1);
            }

            lastIndex = line.lastIndexOf(" ", MAX_LINE_LENGTH);
        }

        System.out.println(line);

    }


}
