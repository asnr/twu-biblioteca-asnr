package com.twu.biblioteca.controller;

import com.twu.biblioteca.controller.*;
import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.InvalidOptionScreen;
import com.twu.biblioteca.view.ListBooksScreen;
import com.twu.biblioteca.view.MainMenuScreen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainMenuStateTest {

    private MainMenuState mainMenu;

    @Before
    public void setUp() {
        User user = new User("000-0001", "password", "A Name", "a@email.com", "1234 5678");
        Users users = new Users(new User[] {}, user);
        BookCollection  emptyBookCollection = new BookCollection(new Book[] {});
        MovieCollection emptyMovieCollection = new MovieCollection(new Movie[] {});
        this.mainMenu = new MainMenuState(
                users, emptyBookCollection, emptyMovieCollection);
    }

    @Test
    public void getScreenReturnsMainMenuScreen() {
        assertEquals(new MainMenuScreen(), mainMenu.getScreen());
    }

    @Test
    public void selectListBooksOptionReturnsListBooksState() {
        AppState state = mainMenu.nextState(MainMenuScreen.ListBooksOption);
        assertTrue(state instanceof BorrowBookState);
    }

    @Test
    public void selectReturnBooksOptionReturnsReturnBooksState() {
        AppState state = mainMenu.nextState(MainMenuScreen.ReturnBooksOption);
        assertTrue(state instanceof ReturnBookState);
    }

    @Test
    public void selectListMoviesOptionReturnsBorrowMovieState() {
        AppState state = mainMenu.nextState(MainMenuScreen.ListMoviesOption);
        assertTrue(state instanceof BorrowMovieState);
    }

    @Test
    public void selectQuitOptionReturnsQuitState() {
        AppState state = mainMenu.nextState(MainMenuScreen.QuitOption);
        assertTrue(state instanceof QuitState);
    }

    @Test
    public void afterInvalidSelectionGetScreenReturnsInvalidOptionScreen() {
        assertEquals(new InvalidOptionScreen(),
                mainMenu.nextState("!!!").getScreen());
    }

    @Test
    public void invalidSelectionEndsUpAtMainMenuScreen() {
        assertEquals(new MainMenuScreen(),
                mainMenu.nextState("!!!").nextState("").getScreen());
    }
}
