package com.twu.biblioteca;

public class IllegalResourceCheckoutException extends Error {
    public IllegalResourceCheckoutException() {
        super(Messages.BOOK_CHECKOUT_FAIL.getMessage());
    }
}
