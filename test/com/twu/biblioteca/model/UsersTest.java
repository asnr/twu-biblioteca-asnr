package com.twu.biblioteca.model;


import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsersTest {

    private User fstUser, sndUser;
    private Users twoUsers;

    @Before
    public void setUp() {
        fstUser = new User("000-0001", "password", "A Name", "a@email.com", "1234 5678");
        sndUser = new User("000-0002", "password2", "Another Name", "b@email.com", "2222 2222");
        twoUsers = new Users(new User[] {fstUser, sndUser});
    }

    @Test
    public void finishLoginCorrectlyReturnsTrue() {
        twoUsers.startLogin(sndUser.getLibraryNum());
        assertTrue(twoUsers.finishLogin("password2"));
    }

    @Test
    public void finishLoginNonExistentUsernameReturnsFalse() {
        twoUsers.startLogin("!!!");
        assertFalse(twoUsers.finishLogin("password"));
    }

    @Test
    public void finishLoginIncorrectPasswordReturnsFalse() {
        twoUsers.startLogin(fstUser.getLibraryNum());
        assertFalse(twoUsers.finishLogin("!!!"));
    }

    @Test
    public void loggedInUserReturnsTheUserSetByFinishLogin() {
        twoUsers.startLogin(fstUser.getLibraryNum());
        twoUsers.finishLogin("password");
        assertEquals(fstUser, twoUsers.loggedInUser());
    }

    @Test
    public void secondConstructorArgumentIsReturnedByLoggedInUser() {
        Users users = new Users(new User[] {}, fstUser);
        assertEquals(fstUser, users.loggedInUser());
    }
}
