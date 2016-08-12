package com.twu.biblioteca.view;

import com.twu.biblioteca.view.ListBooksScreen;
import com.twu.biblioteca.view.QuitScreen;
import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class ListBooksScreenTest {

    private Book cancer, capricorn;

    @Before
    public void setUp() {
        this.cancer = new Book("HM01", "The Tropic of Cancer",
                "Henry Miller", Year.of(1934));
        this.capricorn = new Book("HM02", "The Tropic of Capricorn",
                "Henry Miller", Year.of(1938));
    }

    @Test
    public void printScreenNoBooks() {
        Screen screen = new ListBooksScreen(new Book[] {});
        String expected = ListBooksScreen.noBooksMsg;
        assertEquals(expected, screen.printScreen());
    }

    @Test
    public void printScreenOneBook() {
        Screen screen = new ListBooksScreen(new Book[] {cancer});
        String expected = ListBooksScreen.screenStart + "\n  " + cancer.toString()
                + ListBooksScreen.screenEnd;
        assertEquals(expected, screen.printScreen());
    }

    @Test
    public void printScreenTwoBooks() {
        Screen screen = new ListBooksScreen(new Book[] {cancer, capricorn});
        String expected = ListBooksScreen.screenStart
                + "\n  " + cancer.toString() + "\n  " + capricorn.toString()
                + ListBooksScreen.screenEnd;
        assertEquals(expected, screen.printScreen());
    }

    @Test
    public void listBooksScreenNotEqualToQuitScreen() {
        ListBooksScreen screen = new ListBooksScreen(new Book[] {});
        assertFalse(screen.equals(new QuitScreen()));
    }

    @Test
    public void differentBooksNotEqual() {
        ListBooksScreen fstScreen = new ListBooksScreen(new Book[] {cancer});
        ListBooksScreen sndScreen = new ListBooksScreen(new Book[] {capricorn});
        assertNotEquals(fstScreen, sndScreen);
    }

    @Test
    public void objectsNoBooksAreEqual() {
        assertEquals(new ListBooksScreen(new Book[] {}), new ListBooksScreen(new Book[] {}));
    }

    @Test
    public void objectsWithTheSameBookAreEqual() {
        ListBooksScreen fstScreen = new ListBooksScreen(new Book[] {cancer});
        ListBooksScreen sndScreen = new ListBooksScreen(new Book[] {cancer});
        assertEquals(fstScreen, sndScreen);
    }
}
