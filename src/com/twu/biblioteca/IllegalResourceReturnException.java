package com.twu.biblioteca;

public class IllegalResourceReturnException extends Error {
    public IllegalResourceReturnException() {
        super(Messages.BOOK_RETURN_FAIL.getMessage());
    }
}
