package com.twu.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PromptBuilderTest {
    private PromptBuilder pbuilder;
    private Book book;
    private String[] menu = new String[]{"1 - LIST OF BOOKS", "2 - LIST OF LOANS"};
    private String[] actionMenu = new String[]{"0 - GO BACK", "1 - CHECKOUT BOOK"};

    @BeforeEach
    void setUp() {
        pbuilder = new PromptBuilder("List of Books");
        pbuilder.setMenu(menu);
        book = new Book("TDD for Dummies", "TWU", 2000, 1, 1);
        pbuilder.setBooks(new Book[]{book});
        pbuilder.setActionMenu(actionMenu);
    }

    @Test
    void shouldBuildHeader() {
        assertEquals("\n===== LIST OF BOOKS =====\n", pbuilder.buildHeader());
    }

    @Test
    void shouldSetAndGetMenu() {
        assertArrayEquals(menu, pbuilder.getMenu());
    }

    @Test
    void shouldBuildMenu() {
        assertEquals("1 - LIST OF BOOKS\n2 - LIST OF LOANS\n", pbuilder.buildMenu());
    }

    @Test
    void shouldSetAndGetBooks() {
        assertArrayEquals(new Book[]{book}, pbuilder.getBooks());
    }

    @Test
    void shouldBuildBooks() {
        assertEquals("1 - TDD for Dummies                                   TWU                 2000     Available\n"
                , pbuilder.buildBooks());
    }

    @Test
    void shouldReturnEmptyWhenBookListIsEmpty() {
        pbuilder.setBooks(new Book[]{});
        assertEquals("There are none at the moment", pbuilder.buildBooks());
    }

    @Test
    void shouldSetAndGetActionMenu() {
        assertArrayEquals(actionMenu, pbuilder.getActionMenu());
    }

    @Test
    void shouldBuildActionMenu() {
        assertEquals("0 - GO BACK\n1 - CHECKOUT BOOK\n", pbuilder.buildActionMenu());
    }
}
