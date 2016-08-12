package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.BookCollection;
import com.twu.biblioteca.model.MovieCollection;
import com.twu.biblioteca.model.NoSuchBookException;
import com.twu.biblioteca.view.ListBooksScreen;
import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.view.SuccessfulCheckoutScreen;
import com.twu.biblioteca.view.UnsuccessfulCheckoutScreen;


public class BorrowBookState implements AppState {

    private enum BorrowStage {
        ListBooks, SuccessfulCheckout, UnsuccessfulCheckout,
        Finished
    }

    private BookCollection books;
    private MovieCollection movies;

    private BorrowStage stage;

    public BorrowBookState(BookCollection books, MovieCollection movies) {
        this.books = books;
        this.movies = movies;
        this.stage = BorrowStage.ListBooks;
    }

    @Override
    public AppState nextState(String input) {
        switch (stage) {
            case ListBooks:
                stage = listBooksNextStage(input);
                break;
            case SuccessfulCheckout:
                stage = BorrowStage.ListBooks;
                break;
            case UnsuccessfulCheckout:
                stage = BorrowStage.ListBooks;
            default:
                break;
        }
        if (stage == BorrowStage.Finished) {
            return new MainMenuState(books, movies);
        } else {
            return this;
        }
    }

    @Override
    public Screen getScreen() {
        switch (stage) {
            case ListBooks:
                return new ListBooksScreen(books.availableBooks());
            case SuccessfulCheckout:
                return new SuccessfulCheckoutScreen();
            case UnsuccessfulCheckout:
                return new UnsuccessfulCheckoutScreen();
            default:
                return null;
        }

    }

    private BorrowStage listBooksNextStage(String input) {
        if (input.equals("")) {
            return BorrowStage.Finished;
        }

        Book book;
        try {
            book = books.getBook(input);
        } catch (NoSuchBookException e) {
            book = null;
        }

        BorrowStage newStage;
        if (book != null && book.isAvailable()) {
            book.checkout();
            newStage = BorrowStage.SuccessfulCheckout;
        } else {
            newStage = BorrowStage.UnsuccessfulCheckout;
        }

        return newStage;
    }
}
