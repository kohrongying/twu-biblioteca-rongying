package com.twu.biblioteca;

import java.time.LocalDate;

public class BookLoan {
    private Book book;
    private LocalDate dateBorrowed;
    private LocalDate dateReturned;

    public BookLoan(LocalDate dateBorrowed, Book bookLoaned) {
        try {
            bookLoaned.checkoutBook();
            this.dateBorrowed = dateBorrowed;
            this.book = bookLoaned;
        } catch (IllegalBookCheckoutException e) {
            System.out.println(e.getMessage());
        }
    }

    public Book getBook() {
        return this.book;
    }

    public LocalDate getDateReturned() {
        return this.dateReturned;
    }

    public void returnBook() {
        try {
            this.book.returnBook();
            this.dateReturned = LocalDate.now();
        } catch (IllegalBookReturnException e) {
            System.out.println(e.getMessage());
        }

    }

    public Boolean isActive() {
        if (this.dateReturned == null) {
            return true;
        }
        return false;
    }
}
