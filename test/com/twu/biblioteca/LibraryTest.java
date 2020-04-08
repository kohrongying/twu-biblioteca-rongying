package com.twu.biblioteca;

import org.junit.Test;
import org.junit.Ignore;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LibraryTest {

    @Test
    public void shouldGetListOfAvailableBooksWhenDisplayingListOfBooks() {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        Book book2 = new Book("The Agile Brown Fox Jumped Over the Waterfall", "Bartin Howler", 2010, 1, 1);
        Book book3 = new Book("Pear Programming", "Fruity Peeler", 2012, 1, 0);
        Book[] books = new Book[]{book, book2, book3};
        Library library = new Library(books);
        assertThat(library.getAvailableBooks(), is(new Book[]{book, book2}));
    }
}
