package com.twu.biblioteca.controller;


import com.twu.biblioteca.controller.AppState;
import com.twu.biblioteca.controller.BorrowBookState;
import com.twu.biblioteca.controller.BorrowMovieState;
import com.twu.biblioteca.controller.MainMenuState;
import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.ListMoviesScreen;
import com.twu.biblioteca.view.SuccessfulMovieCheckoutScreen;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;

public class BorrowMovieStateTest {

    private Users users;

    private BookCollection emptyBookCollection;

    private Movie fstMovie, sndMovie;
    private MovieCollection emptyMovieCollection;
    private MovieCollection singleMovieCollection;

    @Before
    public void setUp() {
        User user = new User("000-0001", "password", "A Name", "a@email.com", "1234 5678");
        this.users = new Users(new User[] {}, user);

        this.fstMovie = new Movie("M001", "A Movie", "A Director", Year.of(1978), 1);
        this.sndMovie = new Movie("M002", "Another Movie",
                "Another Director", Year.of(2016));
        this.emptyBookCollection = new BookCollection(new Book[] {});
        this.emptyMovieCollection = new MovieCollection(new Movie[] {});
        this.singleMovieCollection = new MovieCollection(new Movie[] {fstMovie});
    }

    @Test
    public void firstScreenIsListMoviesScreen() {
        AppState state = new BorrowMovieState(
                users, emptyBookCollection, emptyMovieCollection);
        assertEquals(new ListMoviesScreen(new Movie[] {}), state.getScreen());
    }

    @Test
    public void firstScreenOnlyListsAvailableMovies() {
        AppState state = new BorrowMovieState(users, emptyBookCollection,
                new MovieCollection(new Movie[] {fstMovie, sndMovie}));
        fstMovie.checkout();
        assertEquals(new ListMoviesScreen(new Movie[] {sndMovie}), state.getScreen());
    }

    @Test
    public void listMoviesReturnsNullOnEmptyInput() {
        AppState state = new BorrowMovieState(
                users, emptyBookCollection, emptyMovieCollection);
        assertEquals(MainMenuState.class, state.nextState("").getClass());
    }

    @Test
    public void correctlyCheckoutMovieGoesToSuccessfulMovieCheckoutScreen() {
        AppState state = new BorrowMovieState(
                users, emptyBookCollection, singleMovieCollection);
        state = state.nextState(fstMovie.getBarcode());
        assertEquals(new SuccessfulMovieCheckoutScreen(), state.getScreen());
    }

    @Test
    public void correctlyCheckoutMovieEndsAtListMovieScreen() {
        AppState state = new BorrowMovieState(
                users, emptyBookCollection, singleMovieCollection);
        state = state.nextState(fstMovie.getBarcode()).nextState("");
        assertEquals(new ListMoviesScreen(new Movie[] {}), state.getScreen());
    }

    @Test
    public void checkoutNonexistentMovieFailsStaysInListMovies() {
        AppState state = new BorrowMovieState(
                users, emptyBookCollection, singleMovieCollection);
        state = state.nextState("!!!");
        assertEquals(new ListMoviesScreen(new Movie[] {fstMovie}), state.getScreen());
    }

    @Test
    public void checkoutAlreadyCheckedOutMovieFailsStaysInListMovies() {
        AppState state = new BorrowMovieState(
                users, emptyBookCollection, singleMovieCollection);
        fstMovie.checkout();
        state = state.nextState(fstMovie.getBarcode());
        assertEquals(new ListMoviesScreen(new Movie[] {}), state.getScreen());
    }
}
