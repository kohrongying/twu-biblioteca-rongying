package com.twu.biblioteca;

public class UnauthorizedException extends Error {
    public UnauthorizedException() {
        super(Messages.UNAUTHORIZED.getMessage());
    }
}
