package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.BookCollection;
import com.twu.biblioteca.model.MovieCollection;
import com.twu.biblioteca.view.InvalidOptionScreen;
import com.twu.biblioteca.view.MainMenuScreen;
import com.twu.biblioteca.view.Screen;

public class MainMenuState implements AppState {

    private enum MainMenuStage {
        MainMenu, InvalidOption
    }

    private BookCollection books;
    private MovieCollection movies;
    private MainMenuStage stage;

    public MainMenuState(BookCollection books, MovieCollection movies) {
        this.books = books;
        this.movies = movies;
        this.stage = MainMenuStage.MainMenu;
    }

    @Override
    public AppState nextState(String input) {

        switch (stage) {
            case MainMenu:
                return menuNextState(input);
            case InvalidOption:
                stage = MainMenuStage.MainMenu;
                return this;
            default:
                return null;
        }
    }

    @Override
    public Screen getScreen() {
        switch (stage){
            case MainMenu:
                return new MainMenuScreen();
            case InvalidOption:
                return new InvalidOptionScreen();
            default:
                return null;
        }
    }


    private AppState menuNextState(String input) {
        switch (input) {
            case MainMenuScreen.ListBooksOption:
                return new BorrowBookState(books, movies);
            case MainMenuScreen.ReturnBooksOption:
                return new ReturnBookState(books, movies);
            case MainMenuScreen.ListMoviesOption:
                return new BorrowMovieState(books, movies);
            case MainMenuScreen.QuitOption:
                return new QuitState(books, movies);
            default:
                stage = MainMenuStage.InvalidOption;
                return this;
        }
    }
}
