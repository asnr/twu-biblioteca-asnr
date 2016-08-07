package com.twu.biblioteca;

import com.twu.biblioteca.view.ReturnBooksScreen;
import com.twu.biblioteca.view.QuitScreen;
import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.model.Book;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class ReturnBooksScreenTest {
    @Test
    public void printScreenNoBooks() {
        Screen screen = new ReturnBooksScreen(new Book[] {});
        String expected = "\nNo books are currently checked out." + ReturnBooksScreen.screenEnd;
        assertEquals(expected, screen.printScreen());
    }

    @Test
    public void printScreenOneBook() {
        Book book = new Book("123", "Foo", "Ms. Bar", Year.of(1999));
        Screen screen = new ReturnBooksScreen(new Book[] {book});
        String expected = "\nBooks that are currently checked out:\n  " + book.toString()
                + ReturnBooksScreen.screenEnd;
        assertEquals(expected, screen.printScreen());
    }

    @Test
    public void printScreenTwoBooks() {
        Book book1 = new Book("101", "Foo", "Ms. Bar", Year.of(1500));
        Book book2 = new Book("102", "Bar", "Mr. Foo", Year.of(1600));
        Screen screen = new ReturnBooksScreen(new Book[] {book1, book2});
        String expected = "\nBooks that are currently checked out:\n  "
                + book1.toString() + "\n  " + book2.toString()
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
        Book book1 = new Book("101", "Foo", "Ms. Bar", Year.of(1500));
        Book book2 = new Book("102", "Bar", "Mr. Foo", Year.of(1600));
        ReturnBooksScreen fstScreen = new ReturnBooksScreen(new Book[] {book1});
        ReturnBooksScreen sndScreen = new ReturnBooksScreen(new Book[] {book2});
        assertNotEquals(fstScreen, sndScreen);
    }

    @Test
    public void objectsNoBooksAreEqual() {
        assertEquals(new ReturnBooksScreen(new Book[] {}), new ReturnBooksScreen(new Book[] {}));
    }

    @Test
    public void objectsWithTheSameBookAreEqual() {
        Book book = new Book("101", "Foo", "Ms. Bar", Year.of(1500));
        ReturnBooksScreen fstScreen = new ReturnBooksScreen(new Book[] {book});
        ReturnBooksScreen sndScreen = new ReturnBooksScreen(new Book[] {book});
        assertEquals(fstScreen, sndScreen);
    }
}
