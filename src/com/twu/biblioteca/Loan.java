package com.twu.biblioteca;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    private Loanable resource;
    private LocalDate dateBorrowed;
    private LocalDate dateReturned;

    public Loan(Loanable resourceLoaned) {
        this.resource = resourceLoaned;
        this.resource.decrementCopiesAvailable();
        this.dateBorrowed = LocalDate.now();
    }

    public LocalDate getDateReturned() {
        return this.dateReturned;
    }

    public Loanable getResource() {
        return this.resource;
    }

    public void returnResource() {
        this.resource.incrementCopiesAvailable();
        this.dateReturned = LocalDate.now();
    }

    public Boolean isOutstanding() {
        if (this.dateReturned == null) {
            return true;
        }
        return false;
    }
}
