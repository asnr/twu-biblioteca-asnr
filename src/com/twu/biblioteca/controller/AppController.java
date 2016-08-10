package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.NoSuchBookException;
import com.twu.biblioteca.view.*;
import com.twu.biblioteca.model.BookCollection;

import static com.twu.biblioteca.controller.AppController.State.*;

public class AppController {

    public enum State {
        MainMenu, MainMenuInvalidOption,
        ListBooks, SuccessfulCheckout, UnsuccessfulCheckout,
        ReturnBooks, SuccessfulReturn, UnsuccessfulReturn,
        ListMovies,
        Finished
    }


    private State state;
    private BookCollection books;
    private Movie[] movies;


    public AppController(BookCollection books, Movie[] movies) {
        this(books, movies, State.MainMenu);
    }

    public AppController(BookCollection books, Movie[] movies, State startState) {
        this.state = startState;
        this.books = books;
        this.movies = movies;
    }


    public Screen getCurrentScreen() {
        switch (state) {
            case MainMenu:
                return new MainMenuScreen();
            case MainMenuInvalidOption:
                return new InvalidOptionScreen();
            case ListBooks:
                return new ListBooksScreen(books.availableBooks());
            case SuccessfulCheckout:
                return new SuccessfulCheckoutScreen();
            case UnsuccessfulCheckout:
                return new UnsuccessfulCheckoutScreen();
            case ReturnBooks:
                return new ReturnBooksScreen(books.checkedOutBooks());
            case SuccessfulReturn:
                return new SuccessfulReturnScreen();
            case UnsuccessfulReturn:
                return new UnsuccessfulReturnScreen();
            case ListMovies:
                return new ListMoviesScreen(movies);
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

                state = updateMainMenuState(input);
                break;

            case MainMenuInvalidOption:

                state = State.MainMenu;
                break;

            case ListBooks:

                state = updateListBooksState(input);
                break;

            case SuccessfulCheckout:
            case UnsuccessfulCheckout:

                state = State.ListBooks;
                break;

            case ReturnBooks:

                state = updateReturnBooksState(input);
                break;

            case SuccessfulReturn:
            case UnsuccessfulReturn:

                state = State.ReturnBooks;
                break;

            case ListMovies:

                state = updateListMoviesState(input);
                break;

            case Finished:

                break;

            default:

                state = State.MainMenu;
                break;
        }
    }


    private State updateMainMenuState(String input) {
        if (input.equals(MainMenuScreen.ListBooksOption)) {

            return State.ListBooks;

        } else if (input.equals(MainMenuScreen.ReturnBooksOption)) {

            return State.ReturnBooks;

        } else if (input.equals(MainMenuScreen.ListMoviesOption)) {

            return State.ListMovies;

        } else if (input.equals(MainMenuScreen.QuitOption)) {

            return State.Finished;

        } else {

            return State.MainMenuInvalidOption;
        }
    }


    private State updateListBooksState(String input) {

        if (input.equals("")) {
            return State.MainMenu;
        }

        State newState;
        Book book;
        try {
            book = books.getBook(input);
        } catch (NoSuchBookException e) {
            book = null;
        }

        if (book != null && book.isAvailable()) {
            book.checkout();
            newState = SuccessfulCheckout;
        } else {
            newState = UnsuccessfulCheckout;
        }

        return newState;
    }


    private State updateReturnBooksState(String input) {

        if (input.equals("")) {
            return State.MainMenu;
        }

        State newState;
        Book book;
        try {
            book = books.getBook(input);
        } catch (NoSuchBookException e) {
            book = null;
        }

        if (book != null && !book.isAvailable()) {
            book.checkin();
            newState = SuccessfulReturn;
        } else {
            newState = UnsuccessfulReturn;
        }

        return newState;
    }

    private State updateListMoviesState(String input) {
        return State.MainMenu;
    }
}
