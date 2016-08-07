package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.NoSuchBookException;
import com.twu.biblioteca.view.*;
import com.twu.biblioteca.model.BookCollection;

import static com.twu.biblioteca.controller.AppController.State.SuccessfulCheckout;

public class AppController {

    public enum State {
        MainMenu, MainMenuInvalidOption, ListBooks, SuccessfulCheckout, Finished
    }

    private State state;
    private BookCollection collection;

    public AppController(BookCollection collection) {
        this(collection, State.MainMenu);
    }

    public AppController(BookCollection collection, State startState) {
        this.state = startState;
        this.collection = collection;
    }

    public Screen getCurrentScreen() {
        switch (state) {
            case MainMenu:
                return new MainMenuScreen();
            case MainMenuInvalidOption:
                return new InvalidOptionScreen();
            case ListBooks:
                return new ListBooksScreen(collection.availableBooks());
            case SuccessfulCheckout:
                return new SuccessfulCheckoutScreen();
            case Finished:
                return new QuitScreen();
            default:
                return new MainMenuScreen();
        }
    }

    public Screen getNextScreen(String input) {

        updateState(input);

        return getCurrentScreen();
    }

    private void updateState(String input) {

        switch (state) {
            case MainMenu:

                if (input.equals("a")) {

                    state = State.ListBooks;

                } else if (input.equals("q")) {

                    state = State.Finished;

                } else {

                    state = State.MainMenuInvalidOption;
                }

                break;

            case MainMenuInvalidOption:

                state = State.MainMenu;
                break;

            case ListBooks:

                if (input.equals("")) {

                    state = State.MainMenu;

                } else {

                    Book book;
                    try {
                        book = collection.getBook(input);
                    } catch (NoSuchBookException e) {
                        book = null;
                    }

                    if (book != null) {
                        book.checkout();
                        state = SuccessfulCheckout;
                    }

                }

                break;

            case SuccessfulCheckout:

                state = State.ListBooks;
                break;

            case Finished:

                break;

            default:

                state = State.MainMenu;
                break;
        }
    }
}
