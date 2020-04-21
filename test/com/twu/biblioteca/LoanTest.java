package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class LoanTest {
    private Book book;
    private Loan loan;

    @BeforeEach
    public void setUp() {
        book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        loan = new Loan(book);
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
        loan.returnResource();
        assertNotNull(loan.getDateReturned());
    }

    @Test
    public void shouldIncrementBookCopiesWhenBookIsReturned() {
        loan.returnResource();
        assertEquals(1, book.getNumCopiesAvailable());
    }

    @Test
    public void loanOutstandingShouldReturnTrueWhenLoanIsCreated() {
        assertEquals(true, loan.isOutstanding());
    }

    @Test
    public void loanOutstandingShouldReturnFalseWhenBookIsReturned() {
        loan.returnResource();
        assertEquals(false, loan.isOutstanding());
    }

    @Test
    void shouldGetBook() {
        assertEquals(book, loan.getResource());
    }
}
