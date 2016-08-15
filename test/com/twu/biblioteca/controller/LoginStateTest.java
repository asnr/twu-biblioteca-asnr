package com.twu.biblioteca.controller;


import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoginStateTest {

    private User user;
    private Users users;
    private BookCollection emptyBookCollection;
    private MovieCollection emptyMovieCollection;
    private AppState state;

    @Before
    public void setUp() {
        this.user = new User("000-0000", "password", "A Name", "a@email.com", "1234 5678");
        this.users = new Users(new User[] {user});
        this.emptyBookCollection = new BookCollection(new Book[] {});
        this.emptyMovieCollection = new MovieCollection(new Movie[] {});
        this.state = new LoginState(users, emptyBookCollection, emptyMovieCollection);
    }

    @Test
    public void defaultScreenIsEnterUsernameScreen() {
        assertEquals(new EnterLibraryNumberScreen(), state.getScreen());
    }

    @Test
    public void enterUsernameScreenGoesToEnterPasswordScreen() {
        state = state.nextState("");
        assertEquals(new EnterPasswordScreen(), state.getScreen());
    }

    @Test
    public void enterUsernameCallsStartLogin() {
        Users mockUsers = mock(Users.class);
        AppState state = new LoginState(mockUsers,
                emptyBookCollection, emptyMovieCollection);

        state.nextState("foo");
        verify(mockUsers).startLogin("foo");
    }

    @Test
    public void failedLoginGoesToUnsuccessfulLoginToEnterUsername() {
        state = state.nextState("").nextState("");
        assertEquals(new UnsuccessfulLoginScreen(), state.getScreen());
        state = state.nextState("");
        assertEquals(new EnterLibraryNumberScreen(), state.getScreen());
    }

    @Test
    public void successfulLoginReturnMainMenuState() {
        state = state.nextState(new String(user.getLibraryNum()))
                .nextState(new String("password"));
        assertTrue(state instanceof MainMenuState);
    }

    @Test
    public void enterPasswordCallsFinishLogin() throws LoginFailedException {
        Users mockUsers = mock(Users.class);
        AppState state = new LoginState(mockUsers,
                emptyBookCollection, emptyMovieCollection);

        state.nextState("foo").nextState("bar");
        verify(mockUsers).finishLogin("bar");
    }
}
