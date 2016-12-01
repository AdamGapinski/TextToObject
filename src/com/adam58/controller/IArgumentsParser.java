package com.adam58.controller;

import java.nio.file.Path;

/**
 * @author Adam Gapiński
 */
public interface IArgumentsParser {
    Path parseDocumentPath(String[] args);
    UserRequest parseUserRequest(String[] args);
}
