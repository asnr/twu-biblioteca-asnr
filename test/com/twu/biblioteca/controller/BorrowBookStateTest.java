package com.twu.biblioteca.controller;


import com.twu.biblioteca.controller.AppState;
import com.twu.biblioteca.controller.BorrowBookState;
import com.twu.biblioteca.controller.MainMenuState;
import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.ListBooksScreen;
import com.twu.biblioteca.view.SuccessfulCheckoutScreen;
import com.twu.biblioteca.view.UnsuccessfulCheckoutScreen;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BorrowBookStateTest {

    private Book fstBook;
    private BookCollection emptyBookCollection;
    private BookCollection singleBookCollection;
    private MovieCollection emptyMovieCollection;
    private AppState stateEmptyCollections;

    @Before
    public void setUp() {
        this.fstBook = new Book("DA01A", "The Hitchhiker's Guide To The Galaxy",
                "Douglas Adams", Year.of(1979));
        this.emptyBookCollection = new BookCollection(new Book[] {});
        this.singleBookCollection = new BookCollection(new Book[] {fstBook});
        this.emptyMovieCollection = new MovieCollection(new Movie[] {});
        this.stateEmptyCollections = new BorrowBookState(
                emptyBookCollection, emptyMovieCollection);
    }

    @Test
    public void defaultScreenIsListBooksScreen() {
        assertEquals(new ListBooksScreen(new Book[] {}),
                stateEmptyCollections.getScreen());
    }

    @Test
    public void emptyInputReturnsMainMenuState() {
        AppState state = stateEmptyCollections.nextState("");
        assertTrue(state instanceof MainMenuState);
    }

    @Test
    public void correctlyCheckoutBookFromListBooks() {
        AppState state = new BorrowBookState(
                singleBookCollection, emptyMovieCollection);
        state = state.nextState(fstBook.getBarcode());
        assertEquals(new SuccessfulCheckoutScreen(), state.getScreen());
    }

    @Test
    public void checkingOutABookCallsCheckout() throws NoSuchBookException {
        String mockBarcode = "001";

        Book mockedBook = mock(Book.class);
        when(mockedBook.isAvailable()).thenReturn(true);

        BookCollection mockedBooks = mock(BookCollection.class);
        when(mockedBooks.getBook(mockBarcode)).thenReturn(mockedBook);

        AppState state = new BorrowBookState(mockedBooks, emptyMovieCollection);
        state.nextState(mockBarcode);
        verify(mockedBook).checkout();
    }

    @Test
    public void successfulCheckoutScreenGoesToListBooks() {
        AppState state = new BorrowBookState(
                singleBookCollection, emptyMovieCollection);
        state = state.nextState(fstBook.getBarcode()).nextState("");
        assertEquals(new ListBooksScreen(new Book[] {}), state.getScreen());
    }

    @Test
    public void checkoutAlreadyCheckedOutBookFails() {
        AppState state = new BorrowBookState(
                singleBookCollection, emptyMovieCollection);
        fstBook.checkout();
        state = state.nextState(fstBook.getBarcode());
        assertEquals(new UnsuccessfulCheckoutScreen(), state.getScreen());
        state = state.nextState("");
        assertEquals(new ListBooksScreen(new Book[] {}), state.getScreen());
    }

    @Test
    public void checkoutNonExistentBookFails() {
        AppState state = new BorrowBookState(
                singleBookCollection, emptyMovieCollection);
        state = state.nextState("!!!");
        assertEquals(new UnsuccessfulCheckoutScreen(), state.getScreen());
        state = state.nextState("");
        assertEquals(new ListBooksScreen(new Book[] {fstBook}), state.getScreen());
    }
}
