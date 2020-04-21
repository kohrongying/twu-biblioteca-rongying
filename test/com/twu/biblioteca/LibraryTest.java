package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertArrayEquals(new Book[]{book, book2}, library.getAvailableResources());
    }

    @Test
    public void shouldGetListOfOutstandingLoans() {
        assertEquals(0, library.getOutstandingLoans().length);
    }

    @Test
    public void shouldCreateBookLoanWhenCheckingOutBook() {
        Loan loan = library.checkoutResource(book);
        assertArrayEquals(new Loan[]{loan}, library.getOutstandingLoans());
    }

    @Test
    public void shouldThrowErrorWhenCheckingOutUnavailableBook() {
        assertThrows(IllegalResourceCheckoutException.class, () -> {
            library.checkoutResource(book3);
        });
    }

    @Test
    public void shouldRemoveBookFromOutstandingLoansAfterReturningBook() {
        Loan loan = library.checkoutResource(book);
        library.returnLoan(loan);
        assertEquals(0, library.getOutstandingLoans().length);
    }

    @Test
    public void shouldThrowErrorWhenReturningInvalidBook() {
        assertThrows(IllegalResourceReturnException.class, () -> {
            Loan loan = library.checkoutResource(book);
            library.returnLoan(loan);
            library.returnLoan(loan);
        });
    }

    @Test
    void shouldReturnBookGivenBookTitle() {
        assertEquals(book, library.getAvailableResourceByTitle("TDD for Dummies"));
    }

    @Test
    void shouldReturnNullBookGivenInvalidBookTitle() {
        assertNull(library.getAvailableResourceByTitle("TDD for Smarties"));
    }

    @Test
    void shouldReturnLoanGivenBookTitle() {
        Loan loan = library.checkoutResource(book);
        assertEquals(loan, library.getOutstandingLoanByTitle("TDD for Dummies"));
    }

    @Test
    void shouldReturnNullLoanGivenInvalidBookTitle() {
        assertNull(library.getOutstandingLoanByTitle("TDD for Dummies"));
    }
}
