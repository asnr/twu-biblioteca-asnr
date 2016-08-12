package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.*;

import static com.twu.biblioteca.controller.AppController.State.*;

public class AppController {

    public enum State {
        Login,
        MainMenu, MainMenuInvalidOption,
        ListBooks, SuccessfulBookCheckout, UnsuccessfulBookCheckout,
        ReturnBooks, SuccessfulReturn, UnsuccessfulReturn,
        ListMovies, SuccessfulMovieCheckout,
        Finished
    }


    private State state;
    private BookCollection books;
    private MovieCollection movies;

    private AppState appState;

    public AppController(BookCollection books, MovieCollection movies) {
        this(books, movies, State.Login);
    }

    public AppController(BookCollection books, MovieCollection movies, State startState) {
        this.state = startState;
        this.books = books;
        this.movies = movies;


    }


    public Screen getCurrentScreen() {
        switch (state) {
            case Login:
                // This is only here because login is the default start screen
                if (appState == null) {
                    appState = new LoginState(books, movies);
                }
                return appState.getScreen();
            case MainMenu:
                return new MainMenuScreen();
            case MainMenuInvalidOption:
                return new InvalidOptionScreen();
            case ListBooks:
                return appState.getScreen();
            case ReturnBooks:
                return appState.getScreen();
            case ListMovies:
                return appState.getScreen();
            case Finished:
                return appState.getScreen();
            default:
                return null;
        }
    }


    public Screen getNextScreen(String input) {

        updateState(input);

        return getCurrentScreen();
    }


    private void updateState(String input) {

        switch (state) {
            case Login:
                if (appState == null) {
                    appState = new LoginState(books, movies);
                }

                appState = appState.nextState(input);
                if (appState instanceof MainMenuState) {
                    state = State.MainMenu;
                }
                break;

            case MainMenu:

                state = updateMainMenuState(input);

                if (state == ListBooks) {
                    appState = new BorrowBookState(books, movies);
                } else if (state == ReturnBooks) {
                    appState = new ReturnBookState(books, movies);
                } else if (state == ListMovies) {
                    appState = new BorrowMovieState(books, movies);
                } else if (state == Finished) {
                    appState = new QuitState(books, movies);
                }

                break;

            case MainMenuInvalidOption:

                state = State.MainMenu;
                break;

            case ListBooks:
                if (appState == null) {
                    appState = new BorrowBookState(books, movies);
                }

                appState = appState.nextState(input);
                if (appState instanceof MainMenuState) {
                    appState = null;
                    state = State.MainMenu;
                }
                break;

            case ReturnBooks:
                if (appState == null) {
                    appState = new ReturnBookState(books, movies);
                }

                appState = appState.nextState(input);
                if (appState instanceof MainMenuState) {
                    appState = null;
                    state = State.MainMenu;
                }
                break;

            case ListMovies:
                if (appState == null) {
                    appState = new BorrowMovieState(books, movies);
                }

                appState = appState.nextState(input);
                if (appState instanceof MainMenuState) {
                    appState = null;
                    state = State.MainMenu;
                }
                break;

            case Finished:

                if (appState == null) {
                    appState = new QuitState(books, movies);
                }

                appState = appState.nextState(input);
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
}
