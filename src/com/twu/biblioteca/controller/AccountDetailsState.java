package com.twu.biblioteca.controller;


import com.twu.biblioteca.model.AccountDetailsScreen;
import com.twu.biblioteca.model.BookCollection;
import com.twu.biblioteca.model.MovieCollection;
import com.twu.biblioteca.model.Users;
import com.twu.biblioteca.view.Screen;

public class AccountDetailsState implements AppState {

    private Users users;
    private BookCollection books;
    private MovieCollection movies;

    public AccountDetailsState(Users users, BookCollection books, MovieCollection movies) {
        this.users = users;
        this.books = books;
        this.movies = movies;
    }

    @Override
    public AppState nextState(String input) {
        return new MainMenuState(users, books, movies);
    }

    @Override
    public Screen getScreen() {
        return new AccountDetailsScreen(users.loggedInUser());
    }
}
