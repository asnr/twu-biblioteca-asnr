package com.twu.biblioteca.controller;

import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.ListBooksScreen;
import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.view.SuccessfulCheckoutScreen;
import com.twu.biblioteca.view.UnsuccessfulCheckoutScreen;


public class BorrowBookState implements AppState {

    private enum BorrowStage {
        ListBooks, SuccessfulCheckout, UnsuccessfulCheckout,
        Finished
    }

    private Users users;
    private BookCollection books;
    private MovieCollection movies;

    private BorrowStage stage;

    public BorrowBookState(Users users, BookCollection books, MovieCollection movies) {
        this.users = users;
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
            return new MainMenuState(users, books, movies);
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
            users.loggedInUser().checkout(book);
            newStage = BorrowStage.SuccessfulCheckout;
        } else {
            newStage = BorrowStage.UnsuccessfulCheckout;
        }

        return newStage;
    }
}
