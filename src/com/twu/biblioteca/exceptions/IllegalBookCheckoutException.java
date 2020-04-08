package com.twu.biblioteca.exceptions;

public class IllegalBookCheckoutException extends Exception {
    public IllegalBookCheckoutException(String message) {
        super(message);
    }
}
