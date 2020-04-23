package com.twu.biblioteca;

public class IllegalResourceCheckoutException extends Error {
    public IllegalResourceCheckoutException() {
        super(Messages.RESOURCE_CHECKOUT_FAIL.getMessage());
    }
}
