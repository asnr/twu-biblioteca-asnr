package com.twu.biblioteca.controller;


import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.ReturnBooksScreen;
import com.twu.biblioteca.view.SuccessfulReturnScreen;
import com.twu.biblioteca.view.UnsuccessfulReturnScreen;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ReturnBookStateTest {

    private User fstUser, sndUser;
    private Users oneUsers;

    private Book fstBook, sndBook;
    private BookCollection emptyBookCollection;
    private BookCollection singleBookCollection;
    private MovieCollection emptyMovieCollection;

    @Before
    public void setUp() {
        this.fstUser = new User(
                "000-0001", "password", "A Name", "a@email.com", "1234 5678");
        this.sndUser = new User(
                "000-0002", "password2", "Another Name", "b@email.com", "2222 2222");
        this.oneUsers = new Users(new User[] {}, this.fstUser);
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
                oneUsers, emptyBookCollection, emptyMovieCollection);
        assertEquals(new ReturnBooksScreen(new Book[] {}), state.getScreen());
    }

    @Test
    public void returnBooksStateCallsCheckedOutBooksOnLoggedInUser() {
        User mockUser = mock(User.class);
        Users users = new Users(new User[] {}, mockUser);

        AppState state = new ReturnBookState(
                users, emptyBookCollection, emptyMovieCollection);
        state.getScreen();
        verify(mockUser).checkedOutBooks();
    }

    @Test
    public void returnBooksScreenOnlyShowsBooksCheckedOutByLoggedInUser() {
        Users users = new Users(new User[] {fstUser}, sndUser);
        BookCollection books = new BookCollection(new Book[] {fstBook, sndBook});

        AppState state = new ReturnBookState(
                users, books, emptyMovieCollection);
        fstUser.checkout(fstBook);
        sndUser.checkout(sndBook);
        assertEquals(new ReturnBooksScreen(new Book[] {sndBook}), state.getScreen());
    }

    @Test
    public void returnBooksReturnsMainMenuStateOnEmptyInput() {
        AppState state = new ReturnBookState(
                oneUsers, emptyBookCollection, emptyMovieCollection);
        state = state.nextState("");
        assertTrue(state instanceof MainMenuState);
    }

    @Test
    public void validBookReturnCallsCheckinOnUser() throws NoSuchBookException, UserDoesNotHoldBookException {
        User mockUser = mock(User.class);
        when(mockUser.doesHold(fstBook)).thenReturn(true);

        Users users = new Users(new User[] {}, mockUser);

        AppState state = new ReturnBookState(
                users, singleBookCollection, emptyMovieCollection);
        state.nextState(fstBook.getBarcode());
        verify(mockUser).checkin(fstBook);
    }

    @Test
    public void validBookReturnShowsSuccessfulReturnScreen() {
        AppState state = new ReturnBookState(
                oneUsers, singleBookCollection, emptyMovieCollection);
        fstUser.checkout(fstBook);
        state = state.nextState(fstBook.getBarcode());
        assertEquals(new SuccessfulReturnScreen(), state.getScreen());
    }


    @Test
    public void validBookReturnEndsAtReturnBooksScreen() {
        fstUser.checkout(fstBook);
        AppState state = new ReturnBookState(
                oneUsers, singleBookCollection, emptyMovieCollection);
        state = state.nextState(fstBook.getBarcode()).nextState("");
        assertEquals(new ReturnBooksScreen(new Book[] {}), state.getScreen());
    }

    @Test
    public void returnNonexistentBookFromReturnBooks() {
        AppState state = new ReturnBookState(
                oneUsers, singleBookCollection, emptyMovieCollection);
        fstUser.checkout(fstBook);
        state = state.nextState("!!!");
        assertEquals(new UnsuccessfulReturnScreen(), state.getScreen());
    }

    @Test
    public void returnNonExistentBookFromReturnBooksEndsAtReturnBooksScreen() {
        AppState state = new ReturnBookState(
                oneUsers, singleBookCollection, emptyMovieCollection);
        fstUser.checkout(fstBook);
        state = state.nextState("!!!").nextState("");
        assertEquals(new ReturnBooksScreen(new Book[] {fstBook}), state.getScreen());
    }

    @Test
    public void returnAvailableBookDoesntCallCheckinOnUser()
            throws UserDoesNotHoldBookException {
        User mockUser = mock(User.class);
        Users users = new Users(new User[] {}, mockUser);

        AppState state = new ReturnBookState(
                users, singleBookCollection, emptyMovieCollection);
        state.nextState(fstBook.getBarcode());
        verify(mockUser, never()).checkin(any(Book.class));
    }

    @Test
    public void returnAvailableBookFromReturnBooks() {
        AppState state = new ReturnBookState(
                oneUsers, singleBookCollection, emptyMovieCollection);
        state = state.nextState(fstBook.getBarcode());
        assertEquals(new UnsuccessfulReturnScreen(), state.getScreen());
    }

    @Test
    public void returnAvailableBookEndsAtReturnBooksScreen() {
        AppState state = new ReturnBookState(
                oneUsers, singleBookCollection, emptyMovieCollection);
        state = state.nextState(fstBook.getBarcode()).nextState("");
        assertEquals(new ReturnBooksScreen(new Book[] {}), state.getScreen());
    }

    @Test
    public void returningBookHeldByAnotherUserDoesNotCallCheckinOnUser()
            throws NoSuchBookException {
        User mockUser = mock(User.class);
        when(mockUser.doesHold(fstBook)).thenReturn(false);

        Users users = new Users(new User[] {fstUser}, mockUser);

        AppState state = new ReturnBookState(
                users, singleBookCollection, emptyMovieCollection);
        fstUser.checkout(fstBook);
        state.nextState(fstBook.getBarcode());
        verify(mockUser, never()).checkout(any(Book.class));
    }
}
