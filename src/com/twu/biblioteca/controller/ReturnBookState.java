package com.twu.biblioteca.controller;


import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.BookCollection;
import com.twu.biblioteca.model.MovieCollection;
import com.twu.biblioteca.model.NoSuchBookException;
import com.twu.biblioteca.view.ReturnBooksScreen;
import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.view.SuccessfulReturnScreen;
import com.twu.biblioteca.view.UnsuccessfulReturnScreen;

public class ReturnBookState implements AppState {

    private enum ReturnBookStage {
        CheckedOutBooks, SuccessfulReturn, UnsuccessfulReturn, Finished
    }

    private BookCollection books;
    private MovieCollection movies;
    private ReturnBookStage stage;

    public ReturnBookState(BookCollection books, MovieCollection movies) {
        this.books = books;
        this.movies = movies;
        this.stage = ReturnBookStage.CheckedOutBooks;
    }

    @Override
    public AppState nextState(String input) {
        switch (stage) {
            case CheckedOutBooks:
                stage = checkedOutBooksUpdateState(input);
                break;
            case SuccessfulReturn:
            case UnsuccessfulReturn:
                stage = ReturnBookStage.CheckedOutBooks;
                break;
            case Finished:
                stage = ReturnBookStage.Finished;
                break;
        }

        if (stage == ReturnBookStage.Finished) {
            return new MainMenuState(books, movies);
        } else {
            return this;
        }
    }

    @Override
    public Screen getScreen() {
        switch (stage) {
            case CheckedOutBooks:
                return new ReturnBooksScreen(books.checkedOutBooks());
            case SuccessfulReturn:
                return new SuccessfulReturnScreen();
            case UnsuccessfulReturn:
                return new UnsuccessfulReturnScreen();
            case Finished:
            default:
                return null;
        }
    }


    private ReturnBookStage checkedOutBooksUpdateState(String input) {
        if (input.equals("")) {
            return ReturnBookStage.Finished;
        }

        Book book;
        try {
            book = books.getBook(input);
        } catch (NoSuchBookException e) {
            book = null;
        }

        ReturnBookStage nextStage;
        if (book != null && !book.isAvailable()) {
            book.checkin();
            nextStage = ReturnBookStage.SuccessfulReturn;
        } else {
            nextStage = ReturnBookStage.UnsuccessfulReturn;
        }

        return nextStage;
    }
}
