package com.twu.biblioteca;

import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.view.SuccessfulCheckoutScreen;
import com.twu.biblioteca.view.InvalidOptionScreen;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SuccessfulCheckoutScreenTest {
    @Test
    public void twoSuccessfulCheckoutScreenObjectAreEqual() {
        Screen screen = new SuccessfulCheckoutScreen();
        assertTrue(screen.equals(new SuccessfulCheckoutScreen()));
    }

    @Test
    public void successfulCheckoutScreenNotEqualToInvalidOptionScreen() {
        Screen screen = new SuccessfulCheckoutScreen();
        assertFalse(screen.equals(new InvalidOptionScreen()));
    }
}
