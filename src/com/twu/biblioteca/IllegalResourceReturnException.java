package com.twu.biblioteca;

public class IllegalResourceReturnException extends Error {
    public IllegalResourceReturnException() {
        super(Messages.RESOURCE_RETURN_FAIL.getMessage());
    }
}
