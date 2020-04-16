package com.twu.biblioteca;

public enum Messages {
    WELCOME("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"),
    INVALID_OPTION("Please select a valid option"),
    BOOK_CHECKOUT_SUCCESS("Thank you! Enjoy the book"),
    BOOK_CHECKOUT_FAIL("Sorry, that book is not available"),
    BOOK_RETURN_SUCCESS("Thank you for returning the book"),
    BOOK_RETURN_FAIL("That is not a valid book to return"),
    QUIT("Thank you for using Biblioteca! Have a nice day!");

    private final String message;

    Messages(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }
}
