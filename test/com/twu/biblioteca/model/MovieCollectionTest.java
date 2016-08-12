package com.twu.biblioteca.model;


import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.MovieCollection;
import com.twu.biblioteca.model.NoSuchMovieException;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MovieCollectionTest {

    private Movie fstMovie, sndMovie;

    @Before
    public void setUp () {
        this.fstMovie= new Movie("M001", "Foo", "Foo Foo", Year.of(1990));
        this.sndMovie = new Movie("M002", "Bar", "Bar Bar", Year.of(1992), 5);
    }

    @Test
    public void emptyMovieCollectionHasNoAvailableMovies() {
        MovieCollection emptyCollection = new MovieCollection(new Movie[] {});
        assertArrayEquals(new Movie[] {}, emptyCollection.availableMovies());
    }

    @Test
    public void singleMovieCollectionHasOneAvailableMovie() {
        MovieCollection collection = new MovieCollection(new Movie[] {fstMovie});
        assertArrayEquals(new Movie[] {fstMovie}, collection.availableMovies());
    }

    @Test
    public void testSingleCheckedOutMovieIsntInAvailableMovies() {
        MovieCollection collection = new MovieCollection(new Movie[] {fstMovie});
        fstMovie.checkout();
        assertArrayEquals(new Movie[] {}, collection.availableMovies());
    }

    @Test
    public void movieInCollectionReturnedByGetMovie() throws NoSuchMovieException {
        MovieCollection collection = new MovieCollection(new Movie[] {fstMovie});
        assertEquals(fstMovie, collection.getMovie(fstMovie.getBarcode()));
    }

    @Test(expected = NoSuchMovieException.class)
    public void getMovieNotInCollectionThrowsException() throws NoSuchMovieException {
        MovieCollection collection = new MovieCollection(new Movie[] {fstMovie});
        collection.getMovie("!!!");
    }

}
