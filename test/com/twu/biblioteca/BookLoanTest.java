package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;

public class BookLoanTest {
    Book book;
    BookLoan loan;

    @Before
    public void setUp() {
        book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        loan = new BookLoan(book);
    }

    @Test
    public void shouldGetBook() {
        assertThat(loan.getBook(), is(book));
    }

    @Test
    public void shouldCheckoutBookWhenNewLoanIsCreated() {
        assertThat(book.getNumCopiesAvailable(), is(0));
    }

    @Test
    public void shouldGetNullDateReturnedWhenBookLoanIsCreated() {
        assertThat(loan.getDateReturned(), is(nullValue()));
    }

    @Test
    public void shouldSetDateReturnedWhenBookIsReturned() {
        loan.returnBook();
        assertThat(loan.getDateReturned(), is(not(nullValue())));
    }

    @Test
    public void shouldReturnTrueWhenLoanIsOutStanding() {
        assertThat(loan.isOutstanding(), is(true));
    }

    @Test
    public void shouldReturnFalseWhenLoanIsFulfilled() {
        loan.returnBook();
        assertThat(loan.isOutstanding(), is(false));
    }
}
