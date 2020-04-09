package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LibraryTest {
    private Book book;
    private Book book2;
    private Book book3;
    private Book[] books;
    private Library library;

    @Before
    public void setUp() {
        book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        book2 = new Book("The Agile Brown Fox Jumped Over the Waterfall", "Marty Howler", 2010, 1, 1);
        book3 = new Book("Pear Programming", "Fruity Peeler", 2012, 1, 0);
        books = new Book[]{book, book2, book3};
        library = new Library(books);
    }

    @Test
    public void shouldGetListOfAvailableBooks() {
        assertThat(library.getAvailableBooks(), is(new Book[]{book, book2}));
    }

    @Test
    public void shouldGetListOfOutstandingLoans() {
        assertThat(library.getOutstandingLoans().length, is(0));
    }

    @Test
    public void shouldCreateBookLoanWhenCheckingOutBook() {
        BookLoan loan = library.checkoutBook(book);
        assertThat(library.getOutstandingLoans(), is(new BookLoan[]{loan}));
    }

    @Test(expected = IllegalBookCheckoutException.class)
    public void shouldThrowErrorWhenCheckingOutUnavailableBook() {
        library.checkoutBook(book3);
    }

    @Test
    public void shouldRemoveBookFromOutstandingLoansAfterReturningBook() {
        BookLoan loan = library.checkoutBook(book);
        library.returnLoan(loan);
        assertThat(library.getOutstandingLoans().length, is(0));
    }

    @Test(expected = IllegalBookReturnException.class)
    public void shouldThrowErrorWhenReturningInvalidBook() {
        BookLoan loan = library.checkoutBook(book);
        library.returnLoan(loan);
        library.returnLoan(loan);
    }
}
