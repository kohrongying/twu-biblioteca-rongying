package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class LoanTest {
    private Book book;
    private Movie movie;
    private Loan loan;
    private Loan movieLoan;


    @Nested
    class BookLoan {
        @BeforeEach
        void setUp() {
            book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
            loan = new Loan(book);

        }

        @Test
        public void shouldDecrementBookCopiesWhenNewBookIsBorrowed() {
            loan.borrowResource();
            assertEquals(0, book.getNumCopiesAvailable());
        }

        @Test
        public void shouldGetNullDateReturnedWhenLoanIsCreated() {
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

    @Nested
    class MovieLoan {
        @BeforeEach
        void setUp() {
            movie = new Movie("Harry Potty and The Travelling Bat", 2020, "Steven Eysberg", 4, 5, 5);
            movieLoan = new Loan(movie);
        }

        @Test
        public void shouldDecrementMovieCopiesWhenNewMovieIsBorrowed() {
            movieLoan.borrowResource();
            assertEquals(4, movie.getNumCopiesAvailable());
        }

        @Test
        public void shouldGetNullDateReturnedWhenLoanIsCreated() {
            assertNull(movieLoan.getDateReturned());
        }

        @Test
        public void shouldSetDateReturnedWhenMovieIsReturned() {
            movieLoan.returnResource();
            assertNotNull(movieLoan.getDateReturned());
        }

        @Test
        public void shouldIncrementMovieCopiesWhenMovieIsReturned() {
            movieLoan.returnResource();
            assertEquals(5, movie.getNumCopiesAvailable());
        }

        @Test
        public void loanOutstandingShouldReturnTrueWhenLoanIsCreated() {
            assertEquals(true, movieLoan.isOutstanding());
        }

        @Test
        public void loanOutstandingShouldReturnFalseWhenMovieIsReturned() {
            movieLoan.returnResource();
            assertEquals(false, movieLoan.isOutstanding());
        }

        @Test
        void shouldGetMovie() {
            assertEquals(movie, movieLoan.getResource());
        }
    }
}
