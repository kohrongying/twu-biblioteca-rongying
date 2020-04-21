package com.twu.biblioteca;

import java.io.Serializable;

public class Book extends Resource implements Serializable, Loanable {
    private String bookTitle;
    private String authorName;
    private int yearPublished;

    public Book(String bookTitle, String authorName, int yearPublished, int numCopiesTotal, int numCopiesAvailable) {
        super(numCopiesTotal, numCopiesAvailable);
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.yearPublished = yearPublished;
    }

    public String getTitle() {
        return this.bookTitle;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public int getYearPublished() {
        return this.yearPublished;
    }

    @Override
    public String toString() {
        String status = this.isAvailable() ? "Available" : "On Loan";
        return String.format("%-" + 50 + "s", this.bookTitle)
                + String.format("%-" + 20 + "s", this.authorName)
                + String.format("%-" + 9 + "s", this.yearPublished)
                + status;

    }
}
