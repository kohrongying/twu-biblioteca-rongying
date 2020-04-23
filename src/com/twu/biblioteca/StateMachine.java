package com.twu.biblioteca;
import java.util.Arrays;

public class StateMachine {
    private Library library;
    private Library movieLibrary;
    private Library bookLibrary;
    private User[] users;
    private User loggedInUser;
    private User currentUser;
    private State currentState;
    private ResourceType currentResourceType;

    public StateMachine(Library bookLibrary, Library movieLibrary, User[] users) {
        this.bookLibrary = bookLibrary;
        this.movieLibrary = movieLibrary;
        this.library = this.bookLibrary; // set default
        this.users = users;
        this.currentState = State.LOGIN;
    }

    public enum State {
        WELCOME, LIBRARY, RESOURCE_CHECKOUT, RESOURCE_RETURN, LOGIN, PASSWORD_INPUT, USER_INFORMATION, QUIT
    }

    public enum ResourceType {
        BOOK, MOVIE
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(State newState) {
        this.currentState = newState;
    }

    public ResourceType getCurrentResourceType() {
        return this.currentResourceType;
    }

    public void setCurrentResourceType(ResourceType currentResourceType) {
        this.currentResourceType = currentResourceType;
    }

    public Library getLibrary() {
        return this.library;
    }

    public User getUserByLibraryID(String libraryID) {
        for (User user : this.users) {
            if (user.getLibraryID().equals(libraryID)) return user;
        }
        return null;
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    public void nextState(String userString) throws NumberFormatException {
        int userInput;
        try {
            switch (this.currentState) {
                case WELCOME:
                    userInput = Integer.parseInt(userString);

                    if (userInput == 1) {
                        this.currentState = State.LIBRARY;
                        this.currentResourceType = ResourceType.BOOK;
                        this.library = this.bookLibrary;
                    } else if (userInput == 2) {
                        this.currentState = State.LIBRARY;
                        this.currentResourceType = ResourceType.MOVIE;
                        this.library = this.movieLibrary;
                    } else if (userInput == 3) {
                        this.currentState = State.USER_INFORMATION;
                    } else {
                        System.out.println(Messages.INVALID_OPTION.getMessage());
                        this.currentState = State.WELCOME;
                    }
                    break;
                case LIBRARY:
                    userInput = Integer.parseInt(userString);

                    if (userInput == 1) {
                        this.currentState = State.RESOURCE_CHECKOUT;
                    } else if (userInput == 2) {
                        this.currentState = State.RESOURCE_RETURN;
                    } else if (userInput == 0) {
                        this.currentState = State.WELCOME;
                    } else {
                        System.out.println(Messages.INVALID_OPTION.getMessage());
                        this.currentState = State.LIBRARY;
                    }
                    break;
                case RESOURCE_CHECKOUT:
                    userInput = Integer.parseInt(userString);
                    if (userInput == 0) {
                        this.currentState = State.LIBRARY;
                        break;
                    }
                    Loanable[] resources = this.library.getAvailableResources();
                    int resourceIndex = userInput - 1;
                    if (resourceIndex >= 0 && resourceIndex < resources.length) {
                        Loanable resource = resources[resourceIndex];
                        this.library.userCheckout(resource, this.loggedInUser);
                        this.currentState = State.LIBRARY;
                    } else {
                        System.out.println(Messages.RESOURCE_CHECKOUT_FAIL.getMessage());
                        this.currentState = State.RESOURCE_CHECKOUT;
                    }
                    break;
                case RESOURCE_RETURN:
                    userInput = Integer.parseInt(userString);
                    if (userInput == 0) {
                        this.currentState = State.LIBRARY;
                        break;
                    }
                    Loan[] loans = this.library.getOutstandingLoansByLibraryID(this.loggedInUser.getLibraryID());
                    int loanIndex = userInput - 1;
                    if (loanIndex >= 0 && loanIndex < loans.length) {
                        Loan loan = loans[loanIndex];
                        this.library.userReturn(loan, this.loggedInUser);
                        this.currentState = State.LIBRARY;
                    } else {
                        System.out.println(Messages.RESOURCE_RETURN_FAIL.getMessage());
                        this.currentState = State.RESOURCE_RETURN;
                    }
                    break;
                case LOGIN:
                    this.currentUser = this.getUserByLibraryID(userString);
                    this.currentState = State.PASSWORD_INPUT;
                    break;
                case PASSWORD_INPUT:
                    if (this.currentUser != null && this.currentUser.verifyPassword(userString)) {
                        this.currentState = State.WELCOME;
                        this.loggedInUser = this.currentUser;
                        this.currentUser = null;
                    } else {
                        this.currentUser = null;
                        System.out.println(Messages.INCORRECT_PASSWORD.getMessage());
                        this.currentState = State.LOGIN;
                    }
                    break;
                case USER_INFORMATION:
                    this.currentState = State.WELCOME;
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println(Messages.INVALID_OPTION.getMessage());
        }
    }

    public void getNextPrompt() {
        PromptBuilder pbuilder;

        switch (this.currentState) {
            case WELCOME:
                pbuilder = new PromptBuilder("Menu");
                pbuilder.setMenu(new String[]{
                        "1. List Of Books",
                        "2. List of Movies",
                        "3. View User Information"
                });
                pbuilder.setActionMenu(new String[]{"Please input the menu option number"});
                break;
            case LIBRARY:
                pbuilder = new PromptBuilder("List of " + this.currentResourceType);
                pbuilder.setResources(this.library.getAvailableResources());
                pbuilder.setActionMenu(new String[]{
                        "0 - GO BACK",
                        "1 - CHECKOUT",
                        "2 - RETURN"
                });
                break;
            case RESOURCE_CHECKOUT:
                pbuilder = new PromptBuilder("Available resource to check out");
                pbuilder.setResources(this.library.getAvailableResources());
                pbuilder.setActionMenu(new String[]{
                        "0 - GO BACK",
                        "Please input the number of the resource you would like to borrow eg. 1"
                });
                break;
            case RESOURCE_RETURN:
                pbuilder = new PromptBuilder("Outstanding Loans");
                Loan[] loans = this.library.getOutstandingLoans();
                Loanable[] booksOnLoan = Arrays.stream(loans)
                        .map(loan -> loan.getResource()).toArray(Loanable[]::new);
                pbuilder.setResources(booksOnLoan);
                pbuilder.setActionMenu(new String[]{
                        "0 - GO BACK",
                        "Please input the number of the resource you would like to return eg. 1"
                });
                break;
            case LOGIN:
                pbuilder = new PromptBuilder("Login");
                pbuilder.setMenu(new String[]{"Please input your library ID"});
                break;
            case PASSWORD_INPUT:
                pbuilder = new PromptBuilder("Password");
                pbuilder.setMenu(new String[]{"Please input your password"});
                break;
            case USER_INFORMATION:
                pbuilder = new PromptBuilder("User Information");
                pbuilder.setMenu(new String[]{this.loggedInUser.toString()});
                pbuilder.setActionMenu(new String[]{"0 - GO BACK"});
                break;
            default:
                pbuilder = new PromptBuilder("Goodbye");
        }
        System.out.println(pbuilder.toString());
    }
}
