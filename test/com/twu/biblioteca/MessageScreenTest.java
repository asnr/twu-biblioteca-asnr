package com.twu.biblioteca;


import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.view.MessageScreen;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MessageScreenTest {

    static class ConcreteMessageOne extends MessageScreen {
        @Override
        public String printScreen() { return null; }
    }

    static class ConcreteMessageTwo extends MessageScreen {
        @Override
        public String printScreen() { return null; }
    }

    @Test
    public void twoConcreteMessageOneObjectsAreEqual() {
        Screen screen = new ConcreteMessageOne();
        assertTrue(screen.equals(new ConcreteMessageOne()));
    }

    @Test
    public void concreteMessageOneNotEqualToTwo() {
        Screen screen = new ConcreteMessageOne();
        assertFalse(screen.equals(new ConcreteMessageTwo()));
    }
}
