package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.IllegalBookCheckoutException;
import com.twu.biblioteca.exceptions.IllegalBookReturnException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookTest {
    @Test
    public void shouldBeAvailableWhenBookHasAvailableCopies() {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        assertThat(book.isAvailable(), is(true));
    }

    @Test
    public void shouldNotBeAvailableWhenBookHasNoAvailableCopies() {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 0);
        assertThat(book.isAvailable(), is(false));
    }

    @Test
    public void shouldGetBookTitle() {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        assertThat(book.getBookTitle(), is("TDD for Dummies"));
    }

    @Test
    public void shouldGetAuthorName() {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        assertThat(book.getAuthorName(), is("TWU"));
    }

    @Test
    public void shouldGetYearPublished() {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        assertThat(book.getYearPublished(), is(2000));
    }

    @Test
    public void shouldGetNumAvailableCopies() {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        assertThat(book.getNumCopiesAvailable(), is(1));
    }

    @Test
    public void shouldDecrementCopiesAvailableWhenCheckout() throws IllegalBookCheckoutException {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        book.checkoutBook();
        assertThat(book.getNumCopiesAvailable(), is(0));
    }

    @Test(expected = IllegalBookCheckoutException.class)
    public void shouldThrowErrorWhenCheckoutBookThatHasNoAvailableCopies() throws IllegalBookCheckoutException {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 0);
        book.checkoutBook();
    }

    @Test
    public void shouldIncrementCopiesAvailableWhenReturned() throws IllegalBookReturnException {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 0);
        book.returnBook();
        assertThat(book.getNumCopiesAvailable(), is(1));
    }

    @Test(expected = IllegalBookReturnException.class)
    public void shouldThrowErrorWhenReturningBookThatIsNotCheckOut() throws IllegalBookReturnException {
        Book book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        book.returnBook();
    }
}