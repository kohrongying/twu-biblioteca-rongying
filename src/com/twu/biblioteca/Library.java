package com.twu.biblioteca;

import java.util.Arrays;

public class Library {
    private Book[] books;

    public Library(Book[] books) {
        this.books = books;
    }

    public Book[] getAvailableBooks() {
        return Arrays.stream(this.books)
                .filter(s -> s.isAvailable())
                .toArray(Book[]::new);
    }


}
