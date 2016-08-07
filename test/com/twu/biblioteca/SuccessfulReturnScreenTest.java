package com.twu.biblioteca;

import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.view.SuccessfulReturnScreen;
import com.twu.biblioteca.view.InvalidOptionScreen;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SuccessfulReturnScreenTest {
    @Test
    public void twoSuccessfulCheckoutScreenObjectAreEqual() {
        Screen screen = new SuccessfulReturnScreen();
        assertTrue(screen.equals(new SuccessfulReturnScreen()));
    }

    @Test
    public void successfulCheckoutScreenNotEqualToInvalidOptionScreen() {
        Screen screen = new SuccessfulReturnScreen();
        assertFalse(screen.equals(new InvalidOptionScreen()));
    }
}
