package com.twu.biblioteca.view;


import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.view.ListBooksScreen;
import com.twu.biblioteca.view.ListMoviesScreen;
import com.twu.biblioteca.view.Screen;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ListMoviesScreenTest {

    private Movie[] emptyMovieArray;
    private Movie movieHope, movieEmpire;

    @Before
    public void setUp() {
        this.emptyMovieArray = new Movie[] {};
        this.movieHope = new Movie("M001", "Foo", "Foo Foo", Year.of(1990));
        this.movieEmpire = new Movie("M002", "Bar", "Bar Bar", Year.of(1992));
    }

    @Test
    public void printScreenEmptyListMoviesScreen() {
        Screen screen = new ListMoviesScreen(emptyMovieArray);
        assertEquals(ListMoviesScreen.noMoviesMsg,
                screen.printScreen());
    }

    @Test
    public void printScreenSingleMovie() {
        Screen screen = new ListMoviesScreen(new Movie[] {movieHope});
        String expected = ListMoviesScreen.screenStart
                + "\n  " + movieHope.toString()
                + ListMoviesScreen.screenEnd;
        assertEquals(expected, screen.printScreen());
    }

    @Test
    public void printScreenTwoMovies() {
        Screen screen = new ListMoviesScreen(new Movie[] {movieHope, movieEmpire});
        String expected = ListMoviesScreen.screenStart
                + "\n  " + movieHope.toString() + "\n  " + movieEmpire.toString()
                + ListMoviesScreen.screenEnd;
        assertEquals(expected, screen.printScreen());
    }

    @Test
    public void twoEmptyListMoviesScreenObjectsAreEqual() {
        Screen screen = new ListMoviesScreen(emptyMovieArray);
        assertEquals(new ListMoviesScreen(emptyMovieArray), screen);
    }

    @Test
    public void listMoviesScreenNotEqualToListBooksScreen() {
        Screen screen = new ListMoviesScreen(emptyMovieArray);
        assertNotEquals(new ListBooksScreen(new Book[] {}), screen);
    }

    @Test
    public void listMoviesScreenWithDifferentMoviesAreNotEqual() {
        Screen screen1 = new ListMoviesScreen(new Movie[] {movieHope});
        Screen screen2 = new ListMoviesScreen(new Movie[] {movieEmpire});
        assertNotEquals(screen1, screen2);
    }

    @Test
    public void listMoviesScreenWithSameMovieAreEqual() {
        Screen screen = new ListMoviesScreen(new Movie[] {movieHope});
        assertEquals(new ListMoviesScreen(new Movie[] {movieHope}), screen);
    }
}
