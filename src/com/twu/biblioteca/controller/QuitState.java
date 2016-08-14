package com.twu.biblioteca.controller;


import com.twu.biblioteca.model.BookCollection;
import com.twu.biblioteca.model.MovieCollection;
import com.twu.biblioteca.model.Users;
import com.twu.biblioteca.view.QuitScreen;
import com.twu.biblioteca.view.Screen;

public class QuitState implements AppState {

    public QuitState(Users users, BookCollection books, MovieCollection movies) {
    }

    @Override
    public AppState nextState(String input) {
        return this;
    }

    @Override
    public Screen getScreen() {
        return new QuitScreen();
    }
}
