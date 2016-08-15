package com.twu.biblioteca.view;


import com.twu.biblioteca.model.AccountDetailsScreen;
import com.twu.biblioteca.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class AccountDetailsScreenTest {

    private User fstUser;

    @Before
    public void setUp() {
        this.fstUser = new User("000-0001", "password", "A Name", "a@email.com", "1234 5678");
    }

    @Test
    public void accountDetailsScreenNotEqualToListQuitScreen() {
        Screen accountDetails = new AccountDetailsScreen(fstUser);
        assertFalse(accountDetails.equals(new QuitScreen()));
    }

    @Test
    public void twoAccountDetailsScreenWithDifferentUsersAreNotEqual() {
        User sndUser = new User(
                "000-0002", "password2", "Another Name", "b@email.com", "2222 2222");
        Screen screen = new AccountDetailsScreen(sndUser);
        assertNotEquals(screen, new AccountDetailsScreen(fstUser));
    }

    @Test
    public void twoAccountDetailsScreenWithTheSameUserAreEqual() {
        Screen screen = new AccountDetailsScreen(fstUser);
        assertEquals(screen, new AccountDetailsScreen(fstUser));
    }
}
