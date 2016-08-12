package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.*;

import static com.twu.biblioteca.controller.AppController.State.*;

public class AppController {

    public enum State {
        Login,
        MainMenu,
        ListBooks,
        ReturnBooks,
        ListMovies,
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

        switch (state) {
            case Login:
                appState = new LoginState(books, movies);
                break;
            case MainMenu:
                appState = new MainMenuState(books, movies);
                break;
            case ListBooks:
                appState = new BorrowBookState(books, movies);
                break;
            case ReturnBooks:
                appState = new ReturnBookState(books, movies);
                break;
            case ListMovies:
                appState = new BorrowMovieState(books, movies);
                break;
            case Finished:
                appState = new QuitState(books, movies);
                break;
            default:
                appState = null;
        }
    }


    public Screen getCurrentScreen() {
        return appState.getScreen();
    }

    public Screen getNextScreen(String input) {

        updateState(input);

        return getCurrentScreen();
    }


    private void updateState(String input) {
        appState = appState.nextState(input);
    }

}
