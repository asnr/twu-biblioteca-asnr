package com.twu.biblioteca;


import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.view.SuccessfulCheckoutScreen;
import com.twu.biblioteca.view.UnsuccessfulCheckoutScreen;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnsuccessfulCheckoutScreenTest {
    @Test
    public void twoUnsuccesfulCheckoutScreensAreEqual() {
        Screen screen = new UnsuccessfulCheckoutScreen();
        assertTrue(screen.equals(new UnsuccessfulCheckoutScreen()));
    }

    @Test
    public void unsuccessfulCheckoutScreenNotEqualToSuccessfulCheckoutScreen() {
        Screen screen = new UnsuccessfulCheckoutScreen();
        assertFalse(screen.equals(new SuccessfulCheckoutScreen()));
    }
}
