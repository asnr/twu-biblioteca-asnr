package com.twu.biblioteca.model;


import java.util.ArrayList;


public class MovieCollection {

    private Movie[] movies;

    public MovieCollection(Movie[] movies) {
        this.movies = movies;
    }

    public Movie getMovie(String barcode) throws NoSuchMovieException {
        Movie movie = null;
        for (Movie currMovie : movies) {
            if (barcode.equals(currMovie.getBarcode())) {
                movie = currMovie;
                break;
            }
        }

        if (movie == null) {
            throw new NoSuchMovieException();
        }

        return movie;
    }

    public Movie[] availableMovies() {
        ArrayList<Movie> availableMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.isAvailable()) {
                availableMovies.add(movie);
            }
        }
        return availableMovies.toArray(new Movie[availableMovies.size()]);
    }
}
