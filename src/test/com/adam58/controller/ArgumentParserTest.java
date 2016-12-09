package com.adam58.controller;


import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;

import static org.junit.Assert.assertEquals;


/**
 * @author Adam Gapiński
 */
public class ArgumentParserTest {
    private IArgumentParser parser = new ArgumentParser();

    @Before
    public void setUp() {
        parser = new ArgumentParser();
    }

    @Test
    public void parseDocumentPath() throws Exception {
        String[] args = {
                "/home/adam/Idea Projects/TextToObject_Lab8/resources/konstytucja.txt/konstytucja.txt", "..."
        };

        Path path = parser.parseDocumentPath(args);

        assertEquals(args[0], path.toString());
    }

    @Test
    public void parseUserRequest() throws Exception {
        String[] args = {
                "/test/konstytucja.txt", "-a"
        };
        UserRequest request;

        request = parser.parseUserRequest(args);
        assertEquals(UserRequest.SingleArticle, request);


        args[1] = "-ar";
        request = parser.parseUserRequest(args);
        assertEquals(UserRequest.ArticleRange, request);

        args[1] = "-c";
        request = parser.parseUserRequest(args);
        assertEquals(UserRequest.Chapter, request);

        args[1] = "-ch";
        request = parser.parseUserRequest(args);
        assertEquals(UserRequest.Chapter, request);
    }

    @Test
    public void parseChapterNumber() throws Exception {
        String[] args = {
                "/test/konstytucja.txt", "-c", "4"
        };

        Integer chapterNumber = parser.parseChapterNumber(args);
        assertEquals(Integer.valueOf(args[2]), chapterNumber);
    }

    @Test
    public void parseArticleNumber() throws Exception {
        String[] args = {
                "/test/konstytucja.txt", "-a", "4"
        };

        Integer chapterNumber = parser.parseArticleNumber(args);
        assertEquals(Integer.valueOf(args[2]), chapterNumber);
    }

    @Test
    public void parseArticleRange() throws Exception {
        String[] args = {
                "/test/konstytucja.txt", "-ar", "4 5"
        };
        int firstArticle = Integer.valueOf(args[2].split(" ")[0]);
        int lastArticle = Integer.valueOf(args[2].split(" ")[1]);

        ArticleRange articleRange = parser.parseArticleRange(args);

        assertEquals(firstArticle, articleRange.firstArticleNumber);
        assertEquals(lastArticle, articleRange.lastArticleNumber);
    }

    @Test(expected = NotEnoughArgumentsException.class)
    public void notEnoughArgumentsExceptionRange() throws Exception {
        String[] args = {
                "/test/konstytucja.txt", "-ar", "4"
        };
        ArticleRange articleRange = parser.parseArticleRange(args);
    }

    @Test(expected = NotEnoughArgumentsException.class)
    public void notEnoughArgumentsException() throws Exception {
        String[] args = {
                "/test/konstytucja.txt"
        };
        UserRequest userRequest = parser.parseUserRequest(args);
    }
}