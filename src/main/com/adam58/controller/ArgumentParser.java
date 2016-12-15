package com.adam58.controller;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author Adam Gapiński
 */
public class ArgumentParser implements IArgumentParser {
    @Override
    public Path parseDocumentPath(String[] args) {
        if (args.length == 0) {
            throw new NotEnoughArgumentsException("No file path specified.");
        }

        try {
            return Paths.get(args[0]);
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("File path " + args[0] + " is invalid");
        }
    }

    @Override
    public UserRequest parseUserRequest(String[] args) {
        if (args.length < 2) {
            throw new NotEnoughArgumentsException("User request not specified");
        }

        String requestString = args[1];
        requestString = requestString.substring(1, requestString.length()).toUpperCase();

        UserRequest userRequest;
        switch (requestString) {
            case "CH":
            case "C":
                userRequest = UserRequest.Chapter;
                break;
            case "A":
            userRequest = UserRequest.SingleArticle;
                break;
            case "AR":
                userRequest = UserRequest.ArticleRange;
                break;
            default:
                throw new IllegalArgumentException("Illegal user request");
        }

        return userRequest;
    }

    @Override
    public int parseChapterNumber(String[] args) {
        if (args.length < 3) {
            throw new NotEnoughArgumentsException("Chapter number not specified");
        }

        return parseChapterNumber(args[2]);
    }

    @Override
    public int parseArticleNumber(String[] args) {
        if (args.length < 3) {
            throw new NotEnoughArgumentsException("Article number not specified");
        }

        return parseNumber(args[2], "article");
    }

    @Override
    public ArticleRange parseArticleRange(String[] args) {
        if (args.length < 3) {
            throw new NotEnoughArgumentsException("Article range numbers are not specified");
        }

        String articleNumberString = args[2];
        int firstArticleNumber;
        int lastArticleNumber;

        String[] stringRange = articleNumberString.split(" ");
        if (stringRange.length == 1) {
            stringRange = articleNumberString.split("-");

            if (stringRange.length == 1) {
                throw new NotEnoughArgumentsException("Article range not specified");
            }
        }

        firstArticleNumber = parseNumber(stringRange[0].trim(), "article");
        lastArticleNumber = parseNumber(stringRange[1].trim(), "article");

        return new ArticleRange(firstArticleNumber, lastArticleNumber);
    }

    private int parseNumber(String stringNumber, String description) {
        int number;

        try {
            number = Integer.valueOf(stringNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("%s is not valid %s number", stringNumber, description));
        }

        return number;
    }

    private int parseChapterNumber(String stringNumber) {
        int number;

        number = parseRomanChapterNumber(stringNumber);

        if (number == -1) {
            number = parseNumber(stringNumber, "chapter");
        }

        return number;
    }

    private int parseRomanChapterNumber(String stringNumber) {
        stringNumber = stringNumber.toUpperCase();

        String[] romanNumber = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII",};

        int index = Arrays.asList(romanNumber).indexOf(stringNumber);

        return index == -1 ? -1 : index + 1;
    }

}

