package com.twu.biblioteca;

public class IllegalBookReturnException extends Error {
    public IllegalBookReturnException(String message) {
        super(message);
    }
}
