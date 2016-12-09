package com.adam58.controller;

/**
 * @author Adam Gapiński
 */
class NotEnoughArgumentsException extends RuntimeException {
    NotEnoughArgumentsException(String message) {
        super(message);
    }
}
