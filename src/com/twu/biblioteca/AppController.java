package com.twu.biblioteca;

public class AppController {

    private enum State {
        MainMenu, MainMenuInvalidOption, ListBooks
    }

    private State state;
    private BookCollection collection;

    public AppController(BookCollection collection) {
        this.state = State.MainMenu;
        this.collection = collection;
    }

    public Screen startScreen() {
        return new MainMenuScreen();
    }

    public Screen processInput(String input) {

        Screen newScreen;

        switch (state) {

            case MainMenu:

                if (input.equals("a")) {
                    state = State.ListBooks;
                    newScreen = new ListBooksScreen(collection.availableBooks());
                } else {
                    state = State.MainMenuInvalidOption;
                    newScreen = new InvalidOptionScreen();
                }
                break;

            case MainMenuInvalidOption:

                state = State.MainMenuInvalidOption;
                newScreen = new MainMenuScreen();
                break;

            default:

                state = State.MainMenu;
                newScreen = new MainMenuScreen();
                break;
        }

        return newScreen;
    }
}
