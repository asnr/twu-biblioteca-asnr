package com.twu.biblioteca.controller;

import com.twu.biblioteca.controller.*;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.BookCollection;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.MovieCollection;
import com.twu.biblioteca.view.InvalidOptionScreen;
import com.twu.biblioteca.view.ListBooksScreen;
import com.twu.biblioteca.view.MainMenuScreen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainMenuStateTest {

    private BookCollection emptyBookCollection;
    private MovieCollection emptyMovieCollection;
    private MainMenuState mainMenu;

    @Before
    public void setUp() {
        this.emptyBookCollection = new BookCollection(new Book[] {});
        this.emptyMovieCollection = new MovieCollection(new Movie[] {});
        this.mainMenu = new MainMenuState(emptyBookCollection, emptyMovieCollection);
    }

    @Test
    public void getScreenReturnsMainMenuScreen() {
        assertEquals(new MainMenuScreen(), mainMenu.getScreen());
    }

    @Test
    public void selectListBooksOptionReturnsListBooksState() {
        assertEquals(BorrowBookState.class,
                mainMenu.nextState(MainMenuScreen.ListBooksOption).getClass());

    }

    @Test
    public void selectReturnBooksOptionReturnsReturnBooksState() {
        assertEquals(ReturnBookState.class,
                mainMenu.nextState(MainMenuScreen.ReturnBooksOption).getClass());
    }

    @Test
    public void selectListMoviesOptionReturnsBorrowMovieState() {
        assertEquals(BorrowMovieState.class,
                mainMenu.nextState(MainMenuScreen.ListMoviesOption).getClass());
    }

    @Test
    public void selectQuitOptionReturnsQuitState() {
        assertEquals(QuitState.class,
                mainMenu.nextState(MainMenuScreen.QuitOption).getClass());
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
