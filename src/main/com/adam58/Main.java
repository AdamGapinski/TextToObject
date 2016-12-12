package com.adam58;

import com.adam58.controller.Controller;
import com.adam58.controller.IController;

/**
 * Created by Adam on 2016-12-12.
 */
public class Main {
    public static void main(String[] args) {
        IController controller = new Controller();
        controller.handleUserInput(args);
    }
}
