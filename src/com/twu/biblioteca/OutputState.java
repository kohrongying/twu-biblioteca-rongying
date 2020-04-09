package com.twu.biblioteca;

public class OutputState {
    private BibliotecaState state;
    private String message;

    public OutputState(BibliotecaState state, String message) {
        this.state = state;
        this.message = message;
    }

    public BibliotecaState getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }
}
