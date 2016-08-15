package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.BookCollection;
import com.twu.biblioteca.model.MovieCollection;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.model.Users;
import com.twu.biblioteca.view.InvalidOptionScreen;
import com.twu.biblioteca.view.MainMenuScreen;
import com.twu.biblioteca.view.Screen;

public class MainMenuState implements AppState {

    private enum MainMenuStage {
        MainMenu, InvalidOption
    }

    private Users users;
    private BookCollection books;
    private MovieCollection movies;

    private MainMenuStage stage;

    public MainMenuState(Users users, BookCollection books, MovieCollection movies) {
        this.users = users;
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
                return new BorrowBookState(users, books, movies);
            case MainMenuScreen.ReturnBooksOption:
                return new ReturnBookState(users, books, movies);
            case MainMenuScreen.ListMoviesOption:
                return new BorrowMovieState(users, books, movies);
            case MainMenuScreen.AccountDetailsOption:
                return new AccountDetailsState(users, books, movies);
            case MainMenuScreen.QuitOption:
                return new QuitState(users, books, movies);
            default:
                stage = MainMenuStage.InvalidOption;
                return this;
        }
    }
}
