package com.twu.biblioteca;

public class IllegalBookCheckoutException extends Error {
    public IllegalBookCheckoutException(String message) {
        super(message);
    }
}
