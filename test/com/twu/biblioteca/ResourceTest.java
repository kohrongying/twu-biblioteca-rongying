package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ResourceTest {
    private Resource resource;
    private Resource unavailableResource;

    @BeforeEach
    void setUp() {
        resource = new Resource(5, 5);
        unavailableResource = new Resource(5, 0);
    }

    @Test
    void shouldReturnTrueIfAvailableCopies() {
        assertTrue(resource.isAvailable());
    }

    @Test
    void shouldReturnFalseIfNoAvailableCopies() {
        assertFalse(unavailableResource.isAvailable());
    }

    @Test
    void shouldGetNumAvailableCopies() {
        assertEquals(5, resource.getNumCopiesAvailable());
    }

    @Test
    void shouldDecrementCopiesAvailable() {
        resource.decrementCopiesAvailable();
        assertEquals(4, resource.getNumCopiesAvailable());
    }

    @Test
    void shouldNotDecrementWhenNoCopiesAvailable() {
        unavailableResource.decrementCopiesAvailable();
        assertEquals(0, unavailableResource.getNumCopiesAvailable());
    }

    @Test
    void shouldIncrementCopiesAvailable() {
        unavailableResource.incrementCopiesAvailable();
        assertEquals(1, unavailableResource.getNumCopiesAvailable());
    }

    @Test
    void shouldNotIncrementWhenCopiesAvailableWillExceedTotal() {
        resource.incrementCopiesAvailable();
        assertEquals(5, resource.getNumCopiesAvailable());
    }
}
