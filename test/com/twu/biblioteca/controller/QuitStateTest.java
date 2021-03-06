package com.twu.biblioteca.controller;


import com.twu.biblioteca.controller.AppState;
import com.twu.biblioteca.controller.QuitState;
import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.QuitScreen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuitStateTest {

    private BookCollection emptyBookCollection;
    private MovieCollection emptyMovieCollection;
    private AppState quitState;

    @Before
    public void setUp() {
        this.emptyBookCollection = new BookCollection(new Book[] {});
        this.emptyMovieCollection = new MovieCollection(new Movie[] {});
        this.quitState = new QuitState(new Users(new User[] {}),
                emptyBookCollection, emptyMovieCollection);
    }

    @Test
    public void getScreenReturnsQuitScreen() {
        assertEquals(new QuitScreen(), quitState.getScreen());
    }

    @Test
    public void afterEmptyInputGetScreenReturnsQuitScreen() {
        assertEquals(new QuitScreen(), quitState.nextState("").getScreen());
    }

    @Test
    public void afterNonemptyInputGetScreenReturnsQuitScreen() {
        assertEquals(new QuitScreen(), quitState.nextState("a").getScreen());
    }
}
