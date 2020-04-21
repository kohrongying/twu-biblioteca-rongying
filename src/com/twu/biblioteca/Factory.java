package com.twu.biblioteca;

public class Factory {

    public static Library createBookLibrary() {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        Book book2 = new Book("The Agile Brown Fox Jumped Over the Waterfall", "Marty Howler", 2010, 1, 1);
        Book book3 = new Book("Pear Programming", "Fruity Peeler", 2012, 1, 0);
        Book[] books = new Book[]{book, book2, book3};
        return new Library(books);
    }
}
