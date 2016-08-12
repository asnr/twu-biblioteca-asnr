package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.BookCollection;
import com.twu.biblioteca.model.MovieCollection;
import com.twu.biblioteca.view.EnterPasswordScreen;
import com.twu.biblioteca.view.EnterUsernameScreen;
import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.view.UnsuccessfulLoginScreen;


public class LoginState implements AppState {

    private enum LoginStage {
        EnterUsername, EnterPassword, Failed, Succeeded
    }

    private BookCollection books;
    private MovieCollection movies;
    private LoginStage stage;
    private String enteredUsername, enteredPassword;

    public LoginState(BookCollection books, MovieCollection movies) {
        this.books = books;
        this.movies = movies;
        this.stage = LoginStage.EnterUsername;
    }

    @Override
    public AppState nextState(String input) {

        switch (stage) {
            case EnterUsername:
                enteredUsername = input;
                stage = LoginStage.EnterPassword;
                break;
            case EnterPassword:
                enteredPassword = input;
                if (enteredUsername.equals("admin") && enteredPassword.equals("password")) {
                    stage = LoginStage.Succeeded;
                } else {
                    stage = LoginStage.Failed;
                }
                break;
            case Failed:
                stage = LoginStage.EnterUsername;
        }

        if (stage == LoginStage.Succeeded) {
            return new MainMenuState(books, movies);
        } else {
            return this;
        }
    }

    @Override
    public Screen getScreen() {
        switch (stage) {
            case EnterUsername:
                return new EnterUsernameScreen();
            case EnterPassword:
                return new EnterPasswordScreen();
            case Failed:
                return new UnsuccessfulLoginScreen();
            default:
                return null;
        }
    }
}
