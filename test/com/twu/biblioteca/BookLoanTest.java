package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class BookLoanTest {
    private Book book;
    private BookLoan loan;

    @BeforeEach
    public void setUp() {
        book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        loan = new BookLoan(book);
    }

    @Test
    public void shouldDecrementBookCopiesWhenNewLoanIsCreated() {
        assertEquals(0, book.getNumCopiesAvailable());
    }

    @Test
    public void shouldGetNullDateReturnedWhenBookLoanIsCreated() {
        assertNull(loan.getDateReturned());
    }

    @Test
    public void shouldSetDateReturnedWhenBookIsReturned() {
        loan.returnBook();
        assertNotNull(loan.getDateReturned());
    }

    @Test
    public void shouldIncrementBookCopiesWhenBookIsReturned() {
        loan.returnBook();
        assertEquals(1, book.getNumCopiesAvailable());
    }

    @Test
    public void loanOutstandingShouldReturnTrueWhenLoanIsCreated() {
        assertEquals(true, loan.isOutstanding());
    }

    @Test
    public void loanOutstandingShouldReturnFalseWhenBookIsReturned() {
        loan.returnBook();
        assertEquals(false, loan.isOutstanding());
    }

    @Test
    void shouldGetBook() {
        assertEquals(book, loan.getBook());
    }
}
