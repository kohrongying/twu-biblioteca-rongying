package com.twu.biblioteca;

public class Book {
    private String bookTitle;
    private String authorName;
    private int yearPublished;
    private int numCopiesTotal = 1;
    private int numCopiesAvailable = 1;

    public Book(String bookTitle, String authorName, int yearPublished, int numCopiesTotal, int numCopiesAvailable) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.yearPublished = yearPublished;
        this.numCopiesTotal = numCopiesTotal;
        this.numCopiesAvailable = numCopiesAvailable;
    }

    public Boolean isAvailable() {
        if (this.numCopiesAvailable > 0) {
            return true;
        }
        return false;
    }

    public String getBookTitle() {
        return this.bookTitle;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public int getYearPublished() {
        return this.yearPublished;
    }

    public int getNumCopiesTotal() {
        return this.numCopiesTotal;
    }

    public int getNumCopiesAvailable() {
        return this.numCopiesAvailable;
    }

    public void checkoutBook() throws IllegalBookCheckoutException {
        if (this.numCopiesAvailable > 0) {
            this.numCopiesAvailable -= 1;
        } else {
            throw new IllegalBookCheckoutException("Sorry, that book is not available");
        }
    }

    public void returnBook() throws IllegalBookReturnException {
        if (this.numCopiesAvailable + 1 > this.numCopiesTotal) {
            throw new IllegalBookReturnException("That is not a valid book to return");
        } else {
            this.numCopiesAvailable += 1;
        }
    }

}
