package com.twu.biblioteca;

public enum BibliotecaState {

    Welcome {
        public String message() {
            return "\n"
                    + "====== MENU ======"
                    + "1. List of Books"
                    + "\n"
                    + "What would you like to do today? Please input the number eg. 1. ";
        }

        public OutputState nextState(int menuOption) {
            switch (menuOption) {
                case 1:
                    return new OutputState(Library, "");
                default:
                    return new OutputState(Welcome, Messages.INVALID_OPTION.getMessage());
            }
        }
    },

    Library {
        public String message() {
            return "\n"
                    + "===== LIST OF BOOKS ====="
                    + "//TODO"
                    + "What would you like to do? Please input the number eg. 1. "
                    + "\n"
                    + "1. Checkout Book"
                    + "2. Return Book";
        }

        public OutputState nextState(int option) {
            switch (option) {
                case 1:
                    return new OutputState(BibliotecaState.BookCheckout, "");
                case 2:
                    return new OutputState(BibliotecaState.BookReturn, "");
                default:
                    return new OutputState(Library, Messages.INVALID_OPTION.getMessage());
            }
        }

    },

    BookCheckout {
        @Override
        public OutputState nextState(int input) {
            return null;
        }

        @Override
        public String message() {
            return null;
        }
    },

    BookReturn {
        @Override
        public OutputState nextState(int input) {
            return null;
        }

        @Override
        public String message() {
            return null;
        }
    };

    public abstract String message();

    public abstract OutputState nextState(int input);
}
