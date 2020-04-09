package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    private Book book;
    private Book unavailableBook;

    @BeforeEach
    public void setUp() {
        book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        unavailableBook = new Book("TDD for Dummies", "TWU", 2000, 1, 0);
    }

    @Test
    public void shouldBeAvailableWhenBookHasAvailableCopies() {
        assertEquals(book.isAvailable(), true);
    }

    @Test
    public void shouldNotBeAvailableWhenBookHasNoAvailableCopies() {
        assertEquals(unavailableBook.isAvailable(), false);
    }

    @Test
    public void shouldGetBookTitle() {
        assertEquals(book.getBookTitle(), "TDD for Dummies");
    }

    @Test
    public void shouldGetAuthorName() {
        assertEquals(book.getAuthorName(), "TWU");
    }

    @Test
    public void shouldGetYearPubled() {
        assertEquals(book.getYearPublished(), 2000);
    }

    @Test
    public void shouldGetNumAvailableCopies() {
        assertEquals(book.getNumCopiesAvailable(), 1);
    }

    @Test
    public void shouldDecrementCopiesAvailable() {
        unavailableBook.decrementCopiesAvailable();
        assertEquals(unavailableBook.getNumCopiesAvailable(), 0);
    }

    @Test
    public void shouldNotDecrementWhenNoCopiesAvailable() {
        book.decrementCopiesAvailable();
        assertEquals(book.getNumCopiesAvailable(), 0);
    }

    @Test
    public void shouldIncrementCopiesAvailable() {
        unavailableBook.incrementCopiesAvailable();
        assertEquals(unavailableBook.getNumCopiesAvailable(), 1);
    }

    @Test
    public void shouldNotIncrementWhenCopiesAvailableWillExceedTotal() {
        book.incrementCopiesAvailable();
        assertEquals(book.getNumCopiesAvailable(), 1);
    }
}