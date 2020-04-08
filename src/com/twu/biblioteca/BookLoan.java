package com.twu.biblioteca;

import java.time.LocalDate;

public class BookLoan {
    private Book book;
    private LocalDate dateBorrowed;
    private LocalDate dateReturned;

    public BookLoan(Book bookLoaned) {
        this.book = bookLoaned;
        this.book.decrementCopiesAvailable();
        this.dateBorrowed = LocalDate.now();
    }

    public LocalDate getDateReturned() {
        return this.dateReturned;
    }

    public void returnBook() {
        this.book.incrementCopiesAvailable();
        this.dateReturned = LocalDate.now();
    }

    public Boolean isOutstanding() {
        if (this.dateReturned == null) {
            return true;
        }
        return false;
    }
}
