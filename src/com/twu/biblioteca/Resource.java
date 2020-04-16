package com.twu.biblioteca;

public class Resource {
    public int numCopiesTotal;
    public int numCopiesAvailable;

    public Resource(int numCopiesTotal, int numCopiesAvailable) {
        this.numCopiesTotal = numCopiesTotal;
        this.numCopiesAvailable = numCopiesAvailable;
    }

    public Boolean isAvailable() {
        if (this.numCopiesAvailable > 0) {
            return true;
        }
        return false;
    }

    public int getNumCopiesAvailable() {
        return this.numCopiesAvailable;
    }

    public void decrementCopiesAvailable() {
        this.numCopiesAvailable = Math.max(0, this.numCopiesAvailable - 1);
    }

    public void incrementCopiesAvailable() {
        this.numCopiesAvailable = Math.min(this.numCopiesTotal, this.numCopiesAvailable + 1);
    }
}
