package com.twu.biblioteca;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    private Loanable resource;
    private LocalDate dateBorrowed;
    private LocalDate dateReturned;
    private String libraryID;

    public Loan(Loanable resourceLoaned) {
        this.resource = resourceLoaned;
        this.dateBorrowed = LocalDate.now();
    }

    public LocalDate getDateReturned() {
        return this.dateReturned;
    }

    public Loanable getResource() {
        return this.resource;
    }

    public void borrowResource() {
        this.resource.decrementCopiesAvailable();
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

    public String getLibraryID() {
        return this.libraryID;
    }

    public void setLibraryID(String libraryID) {
        this.libraryID = libraryID;
    }
}
