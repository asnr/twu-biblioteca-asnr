package com.twu.biblioteca.controller;


import com.twu.biblioteca.controller.AppState;
import com.twu.biblioteca.controller.LoginState;
import com.twu.biblioteca.controller.MainMenuState;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.BookCollection;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.MovieCollection;
import com.twu.biblioteca.view.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginStateTest {

    private BookCollection emptyBookCollection;
    private MovieCollection emptyMovieCollection;
    private AppState state;

    @Before
    public void setUp() {
        this.emptyBookCollection = new BookCollection(new Book[] {});
        this.emptyMovieCollection = new MovieCollection(new Movie[] {});
        this.state = new LoginState(emptyBookCollection, emptyMovieCollection);
    }

    @Test
    public void defaultScreenIsEnterUsernameScreen() {
        assertEquals(new EnterUsernameScreen(), state.getScreen());
    }

    @Test
    public void enterUsernameScreenGoesToEnterPasswordScreen() {
        state = state.nextState("");
        assertEquals(new EnterPasswordScreen(), state.getScreen());
    }

    @Test
    public void failedLoginGoesToUnsuccessfulLoginToEnterUsername() {
        state = state.nextState("").nextState("");
        assertEquals(new UnsuccessfulLoginScreen(), state.getScreen());
        state = state.nextState("");
        assertEquals(new EnterUsernameScreen(), state.getScreen());
    }

    @Test
    public void successfulLoginReturnNullState() {
        state = state.nextState("admin").nextState("password");
        assertTrue(state instanceof MainMenuState);
    }
}
