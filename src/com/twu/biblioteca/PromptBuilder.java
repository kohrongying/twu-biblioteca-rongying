package com.twu.biblioteca;

import java.util.Arrays;

public class PromptBuilder {
    private String header;
    private Book[] books;
    private String[] actionMenu;
    private String[] menu;

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder("");
        strBuilder.append(this.buildHeader());
        if (this.menu != null) {
            strBuilder.append(this.buildMenu());
        }

        if (this.books != null) {
            strBuilder.append(this.buildBooks());
        }

        if (this.actionMenu != null) {
            strBuilder.append("\n\nWhat would you like to do?\n");
            strBuilder.append(this.buildActionMenu());
        }

        strBuilder.append("\nPlease input option number: ");

        return strBuilder.toString();
    }

    public PromptBuilder(String header) {
        this.header = header;
    }

    public String buildHeader() {
        return "\n===== " + this.header.toUpperCase() + " =====\n";
    }

    public String[] getMenu() {
        return menu;
    }

    public void setMenu(String[] menu) {
        this.menu = menu;
    }

    public String buildMenu() {
        String menuOptions = "";
        for (String s : this.menu) {
            menuOptions += s + "\n";
        }
        return menuOptions;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public String buildBooks() {
        String bookList = "";
        if (this.books.length > 0) {
            for (int i = 0; i < this.books.length; i += 1) {
                bookList += (i + 1)
                        + " - "
                        + this.books[i].toString()
                        + "\n";
            }
        } else {
            bookList += "There are none at the moment";
        }
        return bookList;
    }

    public String[] getActionMenu() {
        return this.actionMenu;
    }

    public void setActionMenu(String[] actionMenu) {
        this.actionMenu = actionMenu;
    }

    public String buildActionMenu() {
        String actionOptions = "";
        for (String s : this.actionMenu) {
            actionOptions += s + "\n";
        }
        return actionOptions;
    }
}
