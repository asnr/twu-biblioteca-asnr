package com.twu.biblioteca.controller;


import com.twu.biblioteca.controller.AppState;
import com.twu.biblioteca.controller.MainMenuState;
import com.twu.biblioteca.controller.ReturnBookState;
import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.ListBooksScreen;
import com.twu.biblioteca.view.ReturnBooksScreen;
import com.twu.biblioteca.view.SuccessfulReturnScreen;
import com.twu.biblioteca.view.UnsuccessfulReturnScreen;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReturnBookStateTest {

    private Book fstBook, sndBook;
    private BookCollection emptyBookCollection;
    private BookCollection singleBookCollection;
    private MovieCollection emptyMovieCollection;

    @Before
    public void setUp() {
        this.fstBook = new Book("HM01", "The Tropic of Cancer",
                "Henry Miller", Year.of(1934));
        this.sndBook = new Book("HM02", "The Tropic of Capricorn",
                "Henry Miller", Year.of(1938));
        this.emptyBookCollection = new BookCollection(new Book[] {});
        this.singleBookCollection = new BookCollection(new Book[] {fstBook});
        this.emptyMovieCollection = new MovieCollection(new Movie[] {});
    }

    @Test
    public void defaultScreenIsReturnBooksScreen() {
        AppState state = new ReturnBookState(
                emptyBookCollection, emptyMovieCollection);
        assertEquals(new ReturnBooksScreen(new Book[] {}), state.getScreen());
    }

    @Test
    public void returnBooksScreenOnlyShowsCheckedOutBooks() {
        BookCollection books = new BookCollection(new Book[] {fstBook, sndBook});
        AppState state = new ReturnBookState(books, emptyMovieCollection);
        sndBook.checkout();
        assertEquals(new ReturnBooksScreen(new Book[] {sndBook}), state.getScreen());
    }

    @Test
    public void returnBooksReturnsMainMenuStateOnEmptyInput() {
        AppState state = new ReturnBookState(
                emptyBookCollection, emptyMovieCollection);
        state = state.nextState("");
        assertTrue(state instanceof MainMenuState);
    }

    @Test
    public void returnCheckedOutBookFromReturnBooks() {
        fstBook.checkout();
        AppState state = new ReturnBookState(singleBookCollection, emptyMovieCollection);
        state = state.nextState(fstBook.getBarcode());
        assertEquals(new SuccessfulReturnScreen(), state.getScreen());
    }

    @Test
    public void returnCheckedOutBookEndsAtReturnBooksScreen() {
        fstBook.checkout();
        AppState state = new ReturnBookState(singleBookCollection, emptyMovieCollection);
        state = state.nextState(fstBook.getBarcode()).nextState("");
        assertEquals(new ReturnBooksScreen(new Book[] {}), state.getScreen());
    }

    @Test
    public void returnNonexistentBookFromReturnBooks() {
        fstBook.checkout();
        AppState state = new ReturnBookState(singleBookCollection, emptyMovieCollection);
        state = state.nextState("!!!");
        assertEquals(new UnsuccessfulReturnScreen(), state.getScreen());
    }

    @Test
    public void returnNonExistentBookFromReturnBooksEndsAtReturnBooksScreen() {
        fstBook.checkout();
        AppState state = new ReturnBookState(singleBookCollection, emptyMovieCollection);
        state = state.nextState("!!!").nextState("");
        assertEquals(new ReturnBooksScreen(new Book[] {fstBook}), state.getScreen());
    }

    @Test
    public void returnAvailableBookFromReturnBooks() {
        AppState state = new ReturnBookState(singleBookCollection, emptyMovieCollection);
        state = state.nextState(fstBook.getBarcode());
        assertEquals(new UnsuccessfulReturnScreen(), state.getScreen());
    }

    @Test
    public void returnAvailableBookEndsAtReturnBooksScreen() {
        AppState state = new ReturnBookState(singleBookCollection, emptyMovieCollection);
        state = state.nextState(fstBook.getBarcode()).nextState("");
        assertEquals(new ReturnBooksScreen(new Book[] {}), state.getScreen());
    }
}
