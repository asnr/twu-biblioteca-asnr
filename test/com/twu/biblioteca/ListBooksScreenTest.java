package com.twu.biblioteca;

import com.twu.biblioteca.view.ListBooksScreen;
import com.twu.biblioteca.view.QuitScreen;
import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.model.Book;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class ListBooksScreenTest {
    @Test
    public void printScreenNoBooks() {
        Screen screen = new ListBooksScreen(new Book[] {});
        String expected = "\nNo books available." + ListBooksScreen.screenEnd;
        assertEquals(expected, screen.printScreen());
    }

    @Test
    public void printScreenOneBook() {
        Book book = new Book("123", "Foo", "Ms. Bar", Year.of(1999));
        Screen screen = new ListBooksScreen(new Book[] {book});
        String expected = "\nAvailable books:\n  " + book.toString()
                + ListBooksScreen.screenEnd;
        assertEquals(expected, screen.printScreen());
    }

    @Test
    public void printScreenTwoBooks() {
        Book book1 = new Book("101", "Foo", "Ms. Bar", Year.of(1500));
        Book book2 = new Book("102", "Bar", "Mr. Foo", Year.of(1600));
        Screen screen = new ListBooksScreen(new Book[] {book1, book2});
        String expected = "\nAvailable books:\n  "
                + book1.toString() + "\n  " + book2.toString()
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
        Book book1 = new Book("101", "Foo", "Ms. Bar", Year.of(1500));
        Book book2 = new Book("102", "Bar", "Mr. Foo", Year.of(1600));
        ListBooksScreen fstScreen = new ListBooksScreen(new Book[] {book1});
        ListBooksScreen sndScreen = new ListBooksScreen(new Book[] {book2});
        assertNotEquals(fstScreen, sndScreen);
    }

    @Test
    public void objectsNoBooksAreEqual() {
        assertEquals(new ListBooksScreen(new Book[] {}), new ListBooksScreen(new Book[] {}));
    }

    @Test
    public void objectsWithTheSameBookAreEqual() {
        Book book = new Book("101", "Foo", "Ms. Bar", Year.of(1500));
        ListBooksScreen fstScreen = new ListBooksScreen(new Book[] {book});
        ListBooksScreen sndScreen = new ListBooksScreen(new Book[] {book});
        assertEquals(fstScreen, sndScreen);
    }
}
