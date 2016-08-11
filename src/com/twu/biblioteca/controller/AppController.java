package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.*;

import static com.twu.biblioteca.controller.AppController.State.*;

public class AppController {

    public enum State {
        MainMenu, MainMenuInvalidOption,
        ListBooks, SuccessfulBookCheckout, UnsuccessfulBookCheckout,
        ReturnBooks, SuccessfulReturn, UnsuccessfulReturn,
        ListMovies, SuccessfulMovieCheckout,
        Finished
    }


    private State state;
    private BookCollection books;
    private MovieCollection movies;


    public AppController(BookCollection books, MovieCollection movies) {
        this(books, movies, State.MainMenu);
    }

    public AppController(BookCollection books, MovieCollection movies, State startState) {
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
            case SuccessfulBookCheckout:
                return new SuccessfulCheckoutScreen();
            case UnsuccessfulBookCheckout:
                return new UnsuccessfulCheckoutScreen();
            case ReturnBooks:
                return new ReturnBooksScreen(books.checkedOutBooks());
            case SuccessfulReturn:
                return new SuccessfulReturnScreen();
            case UnsuccessfulReturn:
                return new UnsuccessfulReturnScreen();
            case ListMovies:
                return new ListMoviesScreen(movies.availableMovies());
            case SuccessfulMovieCheckout:
                return new SuccessfulMovieCheckoutScreen();
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

            case SuccessfulBookCheckout:
            case UnsuccessfulBookCheckout:

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

            case SuccessfulMovieCheckout:

                state = State.ListMovies;

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
            newState = SuccessfulBookCheckout;
        } else {
            newState = UnsuccessfulBookCheckout;
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

        if (input.equals("")) {
            return State.MainMenu;
        }

        Movie movie;
        try {
            movie = movies.getMovie(input);
        } catch(NoSuchMovieException e) {
            movie = null;
        }

        State nextState;
        if (movie != null && movie.isAvailable()) {
            movie.checkout();
            nextState = State.SuccessfulMovieCheckout;
        } else {
            nextState = State.ListMovies;
        }

        return nextState;
    }
}
