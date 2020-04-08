package com.twu.biblioteca;

public class IllegalBookCheckoutException extends Exception {
    public IllegalBookCheckoutException(String message) {
        super(message);
    }
}
