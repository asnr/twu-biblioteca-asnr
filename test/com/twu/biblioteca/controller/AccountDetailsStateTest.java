package com.twu.biblioteca.controller;


import com.twu.biblioteca.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountDetailsStateTest {

    private User fstUser;
    private BookCollection emptyBookCollection;
    private MovieCollection emptyMovieCollection;

    @Before
    public void setUp() {
        this.fstUser = new User(
                "000-0001", "password", "A Name", "a@email.com", "1234 5678");
        this.emptyBookCollection = new BookCollection(new Book[] {});
        this.emptyMovieCollection = new MovieCollection(new Movie[] {});
    }

    @Test
    public void emptyInputGoesBackToMainMenuState() {
        AppState state = new AccountDetailsState(
                new Users(new User[] {}, fstUser),
                emptyBookCollection, emptyMovieCollection);
        state = state.nextState("");
        assertTrue(state instanceof MainMenuState);
    }

    @Test
    public void getScreenReturnsAccountDetailsScreen() {
        User sndUser = new User(
                "000-0002", "password2", "Another Name", "b@email.com", "2222 2222");
        Users users = new Users(new User[] {sndUser}, fstUser);
        AppState state = new AccountDetailsState(
                users, emptyBookCollection, emptyMovieCollection);
        assertEquals(new AccountDetailsScreen(fstUser), state.getScreen());
    }
}
