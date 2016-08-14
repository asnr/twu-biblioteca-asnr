package com.twu.biblioteca.model;


import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserTest {
    private User user;
    private Book catch22;

    @Before
    public void setUp() {
        user = new User("000-0001", "password", "A Name", "a@email.com", "1234 5678");
        this.catch22 = new Book("0000", "Catch-22", "Joseph Heller", Year.of(1961));
    }

    @Test
    public void loginMatchesReturnsTrueIfBothPasswordAndLibraryNumMatches() {
        assertTrue(user.loginMatches(new String(user.getLibraryNum()),
                new String("password")));
    }

    @Test
    public void loginMatchesFalseIfOnlyLibraryNumMatches() {
        assertFalse(user.loginMatches(user.getLibraryNum(), "!!!"));
    }

    @Test
    public void loginMatchesFalseIfOnlyPasswordMatches() {
        assertFalse(user.loginMatches("!!!", "password"));
    }

    @Test
    public void checkoutBookCallsCheckoutOnBook() {
        Book mockBook = mock(Book.class);
        user.checkout(mockBook);
        verify(mockBook).checkout();
    }

    @Test
    public void newUserDoesNotHoldBooks() {
        assertFalse(user.doesHold(catch22));
    }

    @Test
    public void doesHoldBookAfterCheckingItOut() {
        user.checkout(catch22);
        assertTrue(user.doesHold(catch22));
    }

    @Test
    public void checkinAfterCheckoutCallsCheckinOnBook()
            throws UserDoesNotHoldBookException {
        Book mockBook = mock(Book.class);
        user.checkout(mockBook);
        user.checkin(mockBook);
        verify(mockBook).checkin();
    }

//    @Test(expected = UserDoesNotHoldBookException.class)
//    public void checkinWithoutCheckingOutThrowsException()
//            throws UserDoesNotHoldBookException {
//        Book mockBook = mock(Book.class);
//        user.checkin(mockBook);
//    }
}
