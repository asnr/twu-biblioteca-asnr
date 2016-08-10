package com.twu.biblioteca.view;


import com.twu.biblioteca.model.Movie;

import java.util.Arrays;

public class ListMoviesScreen implements Screen {

    public static final String screenStart = "\nAvailable movies:\n";
    public static final String screenEnd =
            "\n\nPress Return to go back to the main menu.\n";

    public static final String noMoviesMsg = "\nNo movies available. "
            + "Press Return to go back to the main menu.\n";


    private Movie[] movies;

    public ListMoviesScreen(Movie[] movies) {
        this.movies = movies;
    }

    @Override
    public String printScreen() {
        if (movies.length == 0) {
            return noMoviesMsg;
        }

        StringBuilder toPrint = new StringBuilder();
        toPrint.append(screenStart);
        for (Movie movie : movies) {
            toPrint.append("\n  ").append(movie.toString());
        }
        toPrint.append(screenEnd);
        return toPrint.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        ListMoviesScreen otherScreen = (ListMoviesScreen) obj;
        return Arrays.equals(this.movies, otherScreen.movies);
    }
}
