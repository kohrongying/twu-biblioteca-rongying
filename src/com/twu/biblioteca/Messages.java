package com.twu.biblioteca;

public enum Messages {
    WELCOME("Welcome to Biblioteca. Your one-stop-shop for great resources in Bangalore!"),
    INVALID_OPTION("Please select a valid option"),
    BOOK_CHECKOUT_SUCCESS("Thank you! Enjoy the resource"),
    BOOK_CHECKOUT_FAIL("Sorry, that resource is not available"),
    BOOK_RETURN_SUCCESS("Thank you for returning the resource"),
    BOOK_RETURN_FAIL("That is not a valid resource to return"),
    QUIT("Thank you for using Biblioteca! Have a nice day!"),
    UNAUTHORIZED("Sorry, you have to be logged in to perform that action");

    private final String message;

    Messages(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }
}
