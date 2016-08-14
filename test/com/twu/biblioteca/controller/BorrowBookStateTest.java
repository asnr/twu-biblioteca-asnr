package com.twu.biblioteca.controller;


import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.ListBooksScreen;
import com.twu.biblioteca.view.SuccessfulCheckoutScreen;
import com.twu.biblioteca.view.UnsuccessfulCheckoutScreen;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class BorrowBookStateTest {

    private User user;
    private Users oneUsers;

    private Book fstBook;
    private BookCollection emptyBookCollection;
    private BookCollection singleBookCollection;

    private MovieCollection emptyMovieCollection;
    private AppState stateEmptyCollections;

    @Before
    public void setUp() {
        this.user = new User(
                "000-0001", "password", "A Name", "a@email.com", "1234 5678");
        this.oneUsers = new Users(new User[] {}, this.user);
        this.fstBook = new Book("DA01A", "The Hitchhiker's Guide To The Galaxy",
                "Douglas Adams", Year.of(1979));
        this.emptyBookCollection = new BookCollection(new Book[] {});
        this.singleBookCollection = new BookCollection(new Book[] {fstBook});
        this.emptyMovieCollection = new MovieCollection(new Movie[] {});
        this.stateEmptyCollections = new BorrowBookState(
                oneUsers, emptyBookCollection, emptyMovieCollection);
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
                oneUsers, singleBookCollection, emptyMovieCollection);
        state = state.nextState(fstBook.getBarcode());
        assertEquals(new SuccessfulCheckoutScreen(), state.getScreen());
    }

    @Test
    public void correctlyCheckingOutABookCallsCheckoutOnUser() {
        User mockUser = mock(User.class);
        Users users = new Users(new User[] {}, mockUser);

        AppState state = new BorrowBookState(
                users, singleBookCollection, emptyMovieCollection);
        state.nextState(fstBook.getBarcode());
        verify(mockUser).checkout(fstBook);
    }

    @Test
    public void successfulCheckoutScreenGoesToListBooks() {
        AppState state = new BorrowBookState(
                oneUsers, singleBookCollection, emptyMovieCollection);
        state = state.nextState(fstBook.getBarcode()).nextState("");
        assertEquals(new ListBooksScreen(new Book[] {}), state.getScreen());
    }

    @Test
    public void checkoutAlreadyCheckedOutBookFails() {
        AppState state = new BorrowBookState(
                oneUsers, singleBookCollection, emptyMovieCollection);
        fstBook.checkout();
        state = state.nextState(fstBook.getBarcode());
        assertEquals(new UnsuccessfulCheckoutScreen(), state.getScreen());
        state = state.nextState("");
        assertEquals(new ListBooksScreen(new Book[] {}), state.getScreen());
    }

    @Test
    public void checkoutAlreadyCheckedOutBookDoesntCallCheckoutOnUser() {
        User mockUser = mock(User.class);
        Users users = new Users(new User[] {user}, mockUser);

        AppState state = new BorrowBookState(
                users, singleBookCollection, emptyMovieCollection);
        user.checkout(fstBook);
        state.nextState(fstBook.getBarcode());

        verify(mockUser, never()).checkout(any(Book.class));
    }

    @Test
    public void checkoutNonExistentBookFails() {
        AppState state = new BorrowBookState(
                oneUsers, singleBookCollection, emptyMovieCollection);
        state = state.nextState("!!!");
        assertEquals(new UnsuccessfulCheckoutScreen(), state.getScreen());
        state = state.nextState("");
        assertEquals(new ListBooksScreen(new Book[] {fstBook}), state.getScreen());
    }
}
