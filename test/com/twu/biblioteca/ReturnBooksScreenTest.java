package com.twu.biblioteca;

import com.twu.biblioteca.view.ReturnBooksScreen;
import com.twu.biblioteca.view.QuitScreen;
import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class ReturnBooksScreenTest {

    private Book wonderland, glass;

    @Before
    public void setUp() {
        this.wonderland = new Book("LC01", "Alice's Adventures in Wonderland",
                "Lewis Carroll", Year.of(1865));
        this.glass = new Book("LC02", "Through the Looking-Glass, and What Alice Found There",
                "Lewis Carroll", Year.of(1871));
    }

    @Test
    public void printScreenNoBooks() {
        Screen screen = new ReturnBooksScreen(new Book[] {});
        String expected = ReturnBooksScreen.noBooksMsg;
        assertEquals(expected, screen.printScreen());
    }

    @Test
    public void printScreenOneBook() {
        Screen screen = new ReturnBooksScreen(new Book[] {wonderland});
        String expected = ReturnBooksScreen.screenStart
                + "\n  " + wonderland.toString() + ReturnBooksScreen.screenEnd;
        assertEquals(expected, screen.printScreen());
    }

    @Test
    public void printScreenTwoBooks() {
        Screen screen = new ReturnBooksScreen(new Book[] {wonderland, glass});
        String expected = ReturnBooksScreen.screenStart
                + "\n  " + wonderland.toString() + "\n  " + glass.toString()
                + ReturnBooksScreen.screenEnd;
        assertEquals(expected, screen.printScreen());
    }

    @Test
    public void listBooksScreenNotEqualToQuitScreen() {
        Screen screen = new ReturnBooksScreen(new Book[] {});
        assertFalse(screen.equals(new QuitScreen()));
    }

    @Test
    public void differentBooksNotEqual() {
        ReturnBooksScreen fstScreen = new ReturnBooksScreen(new Book[] {wonderland});
        ReturnBooksScreen sndScreen = new ReturnBooksScreen(new Book[] {glass});
        assertNotEquals(fstScreen, sndScreen);
    }

    @Test
    public void objectsNoBooksAreEqual() {
        assertEquals(new ReturnBooksScreen(new Book[] {}), new ReturnBooksScreen(new Book[] {}));
    }

    @Test
    public void objectsWithTheSameBookAreEqual() {
        ReturnBooksScreen fstScreen = new ReturnBooksScreen(new Book[] {wonderland});
        ReturnBooksScreen sndScreen = new ReturnBooksScreen(new Book[] {wonderland});
        assertEquals(fstScreen, sndScreen);
    }
}
