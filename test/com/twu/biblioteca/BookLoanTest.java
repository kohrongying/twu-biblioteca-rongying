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
        assertEquals(book.getNumCopiesAvailable(), 0);
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
        assertEquals(book.getNumCopiesAvailable(), 1);
    }

    @Test
    public void loanOutstandingShouldReturnTrueWhenLoanIsCreated() {
        assertEquals(loan.isOutstanding(), true);
    }

    @Test
    public void loanOutstandingShouldReturnFalseWhenBookIsReturned() {
        loan.returnBook();
        assertEquals(loan.isOutstanding(), false);
    }
}
