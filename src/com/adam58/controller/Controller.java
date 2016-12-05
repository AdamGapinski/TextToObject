package com.adam58.controller;

import com.adam58.model.DocumentModel;
import com.adam58.model.IDocumentModel;
import com.adam58.view.DocumentView;
import com.adam58.view.IDocumentView;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.nio.file.Path;

/**
 * @author Adam Gapiński
 */
public class
Controller implements IController {
    private IArgumentParser argumentParser = new ArgumentParser();
    private IDocumentModel documentModel = new DocumentModel();
    private IDocumentView documentView = new DocumentView(documentModel);

    @Override
    public void handleUserInput(String[] args) {

        Path path = argumentParser.parseDocumentPath(args);
        UserRequest userRequest = argumentParser.parseUserRequest(args);

        documentModel.loadDocument(path);

        switch (userRequest) {
            case Chapter:
                int chapterNum = argumentParser.parseChapterNumber(args);
                documentView.printChapter(chapterNum);
                break;
            case SingleArticle:
                int articleNum = argumentParser.parseArticleNumber(args);
                documentView.printArticle(articleNum);
                break;
            case ArticleRange:
                ArticleRange articleRange = argumentParser.parseArticleRange(args);
                documentView.printArticleRange(articleRange);
                break;
            default:
                throw new IllegalArgumentException(userRequest + " is not implemented.");
        }
    }
}
