package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InvalidOptionScreenTest {
    @Test
    public void twoInvalidOptionScreenObjectsAreEqual() {
        Screen screen = new InvalidOptionScreen();
        assertTrue(screen.equals(new InvalidOptionScreen()));
    }

    @Test
    public void mainMenuScreenNotEqualToInvalidOptionScreen() {
        Screen screen = new InvalidOptionScreen();
        assertFalse(screen.equals(new MainMenuScreen()));
    }
}
