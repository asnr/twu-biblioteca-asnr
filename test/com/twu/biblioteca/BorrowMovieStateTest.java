package com.twu.biblioteca;


import com.twu.biblioteca.controller.AppState;
import com.twu.biblioteca.controller.BorrowBookState;
import com.twu.biblioteca.controller.BorrowMovieState;
import com.twu.biblioteca.controller.MainMenuState;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.BookCollection;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.MovieCollection;
import com.twu.biblioteca.view.ListMoviesScreen;
import com.twu.biblioteca.view.SuccessfulMovieCheckoutScreen;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;

public class BorrowMovieStateTest {

    private BookCollection emptyBookCollection;

    private Movie fstMovie, sndMovie;
    private MovieCollection emptyMovieCollection;
    private MovieCollection singleMovieCollection;

    @Before
    public void setUp() {
        this.fstMovie = new Movie("M001", "A Movie", "A Director", Year.of(1978), 1);
        this.sndMovie = new Movie("M002", "Another Movie",
                "Another Director", Year.of(2016));
        this.emptyBookCollection = new BookCollection(new Book[] {});
        this.emptyMovieCollection = new MovieCollection(new Movie[] {});
        this.singleMovieCollection = new MovieCollection(new Movie[] {fstMovie});
    }

    @Test
    public void firstScreenIsListMoviesScreen() {
        AppState state = new BorrowMovieState(emptyBookCollection, emptyMovieCollection);
        assertEquals(new ListMoviesScreen(new Movie[] {}), state.getScreen());
    }

    @Test
    public void firstScreenOnlyListsAvailableMovies() {
        AppState state = new BorrowMovieState(emptyBookCollection,
                new MovieCollection(new Movie[] {fstMovie, sndMovie}));
        fstMovie.checkout();
        assertEquals(new ListMoviesScreen(new Movie[] {sndMovie}), state.getScreen());
    }

    @Test
    public void listMoviesReturnsNullOnEmptyInput() {
        AppState state = new BorrowMovieState(emptyBookCollection, emptyMovieCollection);
        assertEquals(MainMenuState.class, state.nextState("").getClass());
    }

    @Test
    public void correctlyCheckoutMovieGoesToSuccessfulMovieCheckoutScreen() {
        AppState state = new BorrowMovieState(emptyBookCollection, singleMovieCollection);
        state = state.nextState(fstMovie.getBarcode());
        assertEquals(new SuccessfulMovieCheckoutScreen(), state.getScreen());
    }

    @Test
    public void correctlyCheckoutMovieEndsAtListMovieScreen() {
        AppState state = new BorrowMovieState(emptyBookCollection, singleMovieCollection);
        state = state.nextState(fstMovie.getBarcode()).nextState("");
        assertEquals(new ListMoviesScreen(new Movie[] {}), state.getScreen());
    }

    @Test
    public void checkoutNonexistentMovieFailsStaysInListMovies() {
        AppState state = new BorrowMovieState(emptyBookCollection, singleMovieCollection);
        state = state.nextState("!!!");
        assertEquals(new ListMoviesScreen(new Movie[] {fstMovie}), state.getScreen());
    }

    @Test
    public void checkoutAlreadyCheckedOutMovieFailsStaysInListMovies() {
        AppState state = new BorrowMovieState(emptyBookCollection, singleMovieCollection);
        fstMovie.checkout();
        state = state.nextState(fstMovie.getBarcode());
        assertEquals(new ListMoviesScreen(new Movie[] {}), state.getScreen());
    }
}
