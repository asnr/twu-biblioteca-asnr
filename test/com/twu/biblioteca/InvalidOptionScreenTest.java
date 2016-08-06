package com.twu.biblioteca;

import com.twu.biblioteca.view.InvalidOptionScreen;
import com.twu.biblioteca.view.MainMenuScreen;
import com.twu.biblioteca.view.Screen;
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
