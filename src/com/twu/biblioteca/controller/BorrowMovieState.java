package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.ListMoviesScreen;
import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.view.SuccessfulMovieCheckoutScreen;


public class BorrowMovieState implements AppState {

    private enum BorrowMovieStage {
        BorrowMovie, SuccessfulCheckout
    }

    private Users users;
    private BookCollection books;
    private MovieCollection movies;
    private BorrowMovieStage stage;

    public BorrowMovieState(Users users,
                            BookCollection books, MovieCollection movies) {
        this.users = users;
        this.books = books;
        this.movies = movies;
        this.stage = BorrowMovieStage.BorrowMovie;
    }

    @Override
    public AppState nextState(String input) {
        switch (stage) {
            case BorrowMovie:
                return borrowMovieNextState(input);
            case SuccessfulCheckout:
                stage = BorrowMovieStage.BorrowMovie;
                return this;
            default:
                return null;
        }
    }

    @Override
    public Screen getScreen() {
        switch (stage) {
            case BorrowMovie:
                return new ListMoviesScreen(movies.availableMovies());
            case SuccessfulCheckout:
                return new SuccessfulMovieCheckoutScreen();
            default:
                return null;
        }
    }


    private AppState borrowMovieNextState(String input) {
        if (input.equals("")) {
            return new MainMenuState(users, books, movies);
        }

        Movie movie;
        try {
            movie = movies.getMovie(input);
        } catch (NoSuchMovieException e) {
            movie = null;
        }

        if (movie != null && movie.isAvailable()) {
            movie.checkout();
            stage = BorrowMovieStage.SuccessfulCheckout;
        } else {
            stage = BorrowMovieStage.BorrowMovie;
        }

        return this;
    }
}
