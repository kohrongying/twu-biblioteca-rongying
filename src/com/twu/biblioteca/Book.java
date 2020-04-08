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

    public int getNumCopiesAvailable() {
        return this.numCopiesAvailable;
    }

    public void decrementCopiesAvailable() {
        this.numCopiesAvailable = Math.max(0, this.numCopiesAvailable - 1);
    }

    public void incrementCopiesAvailable() {
        this.numCopiesAvailable = Math.min(this.numCopiesTotal, this.numCopiesAvailable + 1);
    }
}
