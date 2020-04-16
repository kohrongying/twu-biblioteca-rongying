package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    private Movie movie;
    private Movie unavailableMovie;

    @BeforeEach
    void setUp() {
        movie = new Movie("Harry Potty and The Travelling Bat", 2020, "Steven Eysberg", 4, 5, 5);
        unavailableMovie = new Movie("Captain Singapore and the Lightning Thief", 2009, "Lee Kuching", 0, 1, 0);
    }

    @Test
    void shouldReturnTrueIfAvailableCopies() {
        assertTrue(movie.isAvailable());
    }

    @Test
    void shouldReturnFalseIfNoAvailableCopies() {
        assertFalse(unavailableMovie.isAvailable());
    }

    @Test
    void shouldGetNumAvailableCopies() {
        assertEquals(5, movie.getNumCopiesAvailable());
    }

    @Test
    void shouldDecrementCopiesAvailable() {
        movie.decrementCopiesAvailable();
        assertEquals(4, movie.getNumCopiesAvailable());
    }

    @Test
    void shouldNotDecrementWhenNoCopiesAvailable() {
        unavailableMovie.decrementCopiesAvailable();
        assertEquals(0, unavailableMovie.getNumCopiesAvailable());
    }

    @Test
    void shouldIncrementCopiesAvailable() {
        unavailableMovie.incrementCopiesAvailable();
        assertEquals(1, unavailableMovie.getNumCopiesAvailable());
    }

    @Test
    void shouldNotIncrementWhenCopiesAvailableWillExceedTotal() {
        movie.incrementCopiesAvailable();
        assertEquals(5, movie.getNumCopiesAvailable());
    }

    @Test
    void shouldGetMovieInformationWhenToStringInvoked() {
        assertEquals("Harry Potty and The Travelling Bat      Steven Eysberg      2020     4    Available", movie.toString());
    }
}
