package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(book.isAvailable());
    }

    @Test
    public void shouldNotBeAvailableWhenBookHasNoAvailableCopies() {
        assertFalse(unavailableBook.isAvailable());
    }

    @Test
    public void shouldGetBookTitle() {
        assertEquals("TDD for Dummies", book.getBookTitle());
    }

    @Test
    public void shouldGetAuthorName() {
        assertEquals("TWU", book.getAuthorName());
    }

    @Test
    public void shouldGetYearPublished() {
        assertEquals(2000, book.getYearPublished());
    }

    @Test
    public void shouldGetNumAvailableCopies() {
        assertEquals(1, book.getNumCopiesAvailable());
    }

    @Test
    public void shouldDecrementCopiesAvailable() {
        book.decrementCopiesAvailable();
        assertEquals(0, book.getNumCopiesAvailable());
    }

    @Test
    public void shouldNotDecrementWhenNoCopiesAvailable() {
        unavailableBook.decrementCopiesAvailable();
        assertEquals(0, unavailableBook.getNumCopiesAvailable());
    }

    @Test
    public void shouldIncrementCopiesAvailable() {
        unavailableBook.incrementCopiesAvailable();
        assertEquals(1, unavailableBook.getNumCopiesAvailable());
    }

    @Test
    public void shouldNotIncrementWhenCopiesAvailableWillExceedTotal() {
        book.incrementCopiesAvailable();
        assertEquals(1, book.getNumCopiesAvailable());
    }

    @Test
    void shouldGetBookInformationWhenToStringInvoked() {
        assertEquals("TDD for Dummies                                   TWU                 2000     Available", book.toString());
    }
}