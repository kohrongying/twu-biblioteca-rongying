package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class LibraryTest {
    private Book book;
    private Book book2;
    private Book book3;
    private Book[] books;
    private Library library;

    @BeforeEach
    public void setUp() {
        book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        book2 = new Book("The Agile Brown Fox Jumped Over the Waterfall", "Marty Howler", 2010, 1, 1);
        book3 = new Book("Pear Programming", "Fruity Peeler", 2012, 1, 0);
        books = new Book[]{book, book2, book3};
        library = new Library(books);
    }

    @Test
    public void shouldGetListOfAvailableBooks() {
        assertArrayEquals(new Book[]{book, book2}, library.getAvailableBooks());
    }

    @Test
    public void shouldGetListOfOutstandingLoans() {
        assertEquals(0, library.getOutstandingLoans().length);
    }

    @Test
    public void shouldCreateBookLoanWhenCheckingOutBook() {
        BookLoan loan = library.checkoutBook(book);
        assertArrayEquals(new BookLoan[]{loan}, library.getOutstandingLoans());
    }

    @Test
    public void shouldThrowErrorWhenCheckingOutUnavailableBook() {
        assertThrows(IllegalBookCheckoutException.class, () -> {
            library.checkoutBook(book3);
        });
    }

    @Test
    public void shouldRemoveBookFromOutstandingLoansAfterReturningBook() {
        BookLoan loan = library.checkoutBook(book);
        library.returnLoan(loan);
        assertEquals(0, library.getOutstandingLoans().length);
    }

    @Test
    public void shouldThrowErrorWhenReturningInvalidBook() {
        assertThrows(IllegalBookReturnException.class, () -> {
            BookLoan loan = library.checkoutBook(book);
            library.returnLoan(loan);
            library.returnLoan(loan);
        });
    }

    @Test
    void shouldReturnBookGivenBookTitle() {
        assertEquals(book, library.getAvailableBook("TDD for Dummies"));
    }
}
