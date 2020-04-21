package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

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

    @Nested
    class WithAnonymousUser {
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
    }

    @Nested
    class WithUser {
        private User user;

        @BeforeEach
        void setUp() {
            user = Factory.createUsers()[0];
        }

        @Test
        void shouldGetOutstandingLoansByLibraryID() {
            assertEquals(0, library.getOutstandingLoansByLibraryID(user.getLibraryID()).length);
        }

        @Test
        void shouldCreateBookLoanWhenUserChecksOutBook() {
            Loan loan = library.userCheckout(book, user);
            assertArrayEquals(new Loan[]{loan}, library.getOutstandingLoansByLibraryID(user.getLibraryID()));
        }

        @Test
        void shouldThrowUnauthorizedErrorWhenCheckingOutGivenUserNotLoggedIn() {
            assertThrows(UnauthorizedException.class, () -> {
                library.userCheckout(book, null);
            });
        }

        @Test
        void shouldRemoveBookLoanFromOutstandingWhenUserReturnsBook() {
            Loan loan = library.userCheckout(book, user);
            library.userReturn(loan, user);
            assertEquals(0, library.getOutstandingLoansByLibraryID(user.getLibraryID()).length);
        }

        @Test
        void shouldThrowUnAuthorizedErrorWhenReturningBookGivenUserNotLoggedIn() {
            Loan loan = library.userCheckout(book, user);
            assertThrows(UnauthorizedException.class, () -> {
                library.userReturn(loan, null);
            });
        }

        @Test
        void shouldThrowIllegalBookReturnErrorWhenReturningLoanThatIsNotYours() {
            Loan loan = library.userCheckout(book, user);
            User user2 = Factory.createUsers()[1];
            assertThrows(IllegalResourceReturnException.class, () -> {
                library.userReturn(loan, user2);
            });
        }
    }

}
