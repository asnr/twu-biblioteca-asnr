package com.twu.biblioteca;

import com.twu.biblioteca.view.MainMenuScreen;
import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.view.QuitScreen;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuitScreenTest {
    @Test
    public void twoQuitScreensAreEqual() {
        Screen quitScreen = new QuitScreen();
        assertTrue(quitScreen.equals(new QuitScreen()));
    }

    @Test
    public void quitScreenNotEqualToMainMenu() {
        Screen quitScreen = new QuitScreen();
        assertFalse(quitScreen.equals(new MainMenuScreen()));
    }
}
