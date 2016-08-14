package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.EnterPasswordScreen;
import com.twu.biblioteca.view.EnterUsernameScreen;
import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.view.UnsuccessfulLoginScreen;

import javax.security.auth.login.FailedLoginException;


public class LoginState implements AppState {

    private enum LoginStage {
        EnterUsername, EnterPassword, Failed, Succeeded
    }

    private Users users;
    private BookCollection books;
    private MovieCollection movies;

    private LoginStage stage;
    private String enteredUsername, enteredPassword;

    public LoginState(Users users, BookCollection books, MovieCollection movies) {
        this.users = users;
        this.books = books;
        this.movies = movies;
        this.stage = LoginStage.EnterUsername;
    }

    @Override
    public AppState nextState(String input) {

        switch (stage) {

            case EnterUsername:

                users.startLogin(input);
                enteredUsername = input;
                stage = LoginStage.EnterPassword;
                break;

            case EnterPassword:

                boolean loginSucceeded = users.finishLogin(input);

                if (loginSucceeded) {
                    stage = LoginStage.Succeeded;
                } else {
                    stage = LoginStage.Failed;
                }
                break;

            case Failed:

                stage = LoginStage.EnterUsername;
        }

        if (stage == LoginStage.Succeeded) {
            return new MainMenuState(users, books, movies);
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
