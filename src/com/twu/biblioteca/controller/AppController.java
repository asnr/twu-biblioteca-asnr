package com.twu.biblioteca.controller;

import com.twu.biblioteca.view.*;
import com.twu.biblioteca.model.BookCollection;

public class AppController {

    private enum State {
        MainMenu, MainMenuInvalidOption, ListBooks, Finished
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

                } else if (input.equals("q")) {

                    state = State.Finished;
                    newScreen = new QuitScreen();

                } else {

                    state = State.MainMenuInvalidOption;
                    newScreen = new InvalidOptionScreen();
                }

                break;

            case MainMenuInvalidOption:

                state = State.MainMenuInvalidOption;
                newScreen = new MainMenuScreen();
                break;

            case Finished:

                newScreen = new QuitScreen();
                break;

            default:

                state = State.MainMenu;
                newScreen = new MainMenuScreen();
                break;
        }

        return newScreen;
    }
}
