package com.twu.biblioteca;

import com.twu.biblioteca.model.Movie;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    private Movie ratedMovie, unratedMovie;

    @Before
    public void setUp() {
        this.unratedMovie = new Movie("M123", "A movie", "A Director", Year.of(1950));
        this.ratedMovie = new Movie("M124", "Another movie", "Another Director", Year.of(1950), 5);
    }

    @Test
    public void toStringRatedMovie() {
        assertEquals("[M123] A movie by A Director released 1950 (not rated)",
                unratedMovie.toString());
    }

    @Test
    public void toStringUnratedMovie() {
        assertEquals("[M124] Another movie by Another Director released 1950 (rated 5/10)",
                ratedMovie.toString());
    }
}
