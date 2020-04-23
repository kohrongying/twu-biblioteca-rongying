package com.twu.biblioteca;

public enum Messages {
    WELCOME("Welcome to Biblioteca. Your one-stop-shop for great resources in Bangalore!"),
    INVALID_OPTION("Please select a valid option"),
    RESOURCE_CHECKOUT_SUCCESS("Thank you! Enjoy the resource"),
    RESOURCE_CHECKOUT_FAIL("Sorry, that resource is not available"),
    RESOURCE_RETURN_SUCCESS("Thank you for returning the resource"),
    RESOURCE_RETURN_FAIL("That is not a valid resource to return"),
    QUIT("Thank you for using Biblioteca! Have a nice day!"),
    UNAUTHORIZED("Sorry, you have to be logged in to perform that action"),
    INCORRECT_PASSWORD("Sorry, you have entered an invalid library ID and/or password");

    private final String message;

    Messages(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }
}
