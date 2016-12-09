package com.adam58.controller;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Gapiński
 */
public class IntegrationTest {
    private IController controller;

    @Before
    public void setUp() throws Exception {
        controller = new Controller();

    }

    @Test
    public void handleUserInputReadArticle() throws Exception {
        String[] args = {
                "resources/test/konstytucja.txt", "-a", "41"
        };

        controller.handleUserInput(args);
    }

    @Test
    public void handleUserInputReadArticleRange() throws Exception {
        String[] args = {
                "resources/test/konstytucja.txt", "-AR", "23-65"
        };

        controller.handleUserInput(args);
    }

    @Test
    public void handleUserInputReadChapter() throws Exception {
        String[] args = {
                "resources/test/konstytucja.txt", "-CH", "9"
        };

        controller.handleUserInput(args);
    }

    @Test
    public void handleUserInputElementNotExists() throws Exception {
        String[] args = {
                "resources/test/konstytucja.txt", "-CH", "14"
        };

        controller.handleUserInput(args);

        args[1] = "-A";
        args[2] = "-4";
        controller.handleUserInput(args);

        args[2] = "243";
        controller.handleUserInput(args);
    }


}