package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookTest {
    @Test
    public void getBarCode() {
        Book book = new Book("123", "A book", "An Author", Year.of(2000));
        assertEquals("123", book.getBarcode());
    }

    @Test
    public void getBookTitle() {
        Book book = new Book("456", "A book title", "Foo Bar", Year.of(2000));
        assertEquals("A book title", book.getTitle());
    }

    @Test
    public void getBookAuthor() {
        Book book = new Book("789", "Another title", "Bar Foo", Year.of(2000));
        assertEquals("Bar Foo", book.getAuthor());
    }

    @Test
    public void toStringCatch22() {
        Book book = new Book("123", "Catch-22", "Joseph Heller", Year.of(1961));
        assertEquals("[123] Catch-22 by Joseph Heller published 1961", book.toString());
    }

    @Test
    public void newBookIsAvailable() {
        Book book = new Book("123", "Catch-22", "Joseph Heller", Year.of(1961));
        assertTrue(book.isAvailable());
    }

    @Test
    public void checkedOutBookIsNotAvailable() {
        Book book = new Book("123", "Catch-22", "Joseph Heller", Year.of(1961));
        book.checkout();
        assertFalse(book.isAvailable());
    }

    @Test
    public void returnedBookIsAvailable() {
        Book book = new Book("123", "Catch-22", "Joseph Heller", Year.of(1961));
        book.checkout().checkin();
        assertTrue(book.isAvailable());
    }
    
}
