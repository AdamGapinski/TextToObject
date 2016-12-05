package com.adam58.controller;

import java.nio.file.Path;

/**
 * @author Adam Gapiński
 */
public interface IArgumentParser {
    Path parseDocumentPath(String[] args);
    UserRequest parseUserRequest(String[] args);

    int parseChapterNumber(String[] args);
    int parseArticleNumber(String[] args);
    ArticleRange parseArticleRange(String[] args);
}
