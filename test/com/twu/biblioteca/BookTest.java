package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookTest {
    private Book book;
    private Book unavailableBook;

    @Before
    public void setUp() {
        book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        unavailableBook = new Book("TDD for Dummies", "TWU", 2000, 1, 0);
    }

    @Test
    public void shouldBeAvailableWhenBookHasAvailableCopies() {
        assertThat(book.isAvailable(), is(true));
    }

    @Test
    public void shouldNotBeAvailableWhenBookHasNoAvailableCopies() {
        assertThat(unavailableBook.isAvailable(), is(false));
    }

    @Test
    public void shouldGetBookTitle() {
        assertThat(book.getBookTitle(), is("TDD for Dummies"));
    }

    @Test
    public void shouldGetAuthorName() {
        assertThat(book.getAuthorName(), is("TWU"));
    }

    @Test
    public void shouldGetYearPublished() {
        assertThat(book.getYearPublished(), is(2000));
    }

    @Test
    public void shouldGetNumAvailableCopies() {
        assertThat(book.getNumCopiesAvailable(), is(1));
    }

    @Test
    public void shouldDecrementCopiesAvailable() {
        unavailableBook.decrementCopiesAvailable();
        assertThat(unavailableBook.getNumCopiesAvailable(), is(0));
    }

    @Test
    public void shouldNotDecrementWhenNoCopiesAvailable() {
        book.decrementCopiesAvailable();
        assertThat(book.getNumCopiesAvailable(), is(0));
    }

    @Test
    public void shouldIncrementCopiesAvailable() {
        unavailableBook.incrementCopiesAvailable();
        assertThat(unavailableBook.getNumCopiesAvailable(), is(1));
    }

    @Test
    public void shouldNotIncrementWhenCopiesAvailableWillExceedTotal() {
        book.incrementCopiesAvailable();
        assertThat(book.getNumCopiesAvailable(), is(1));
    }
}