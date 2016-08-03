package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookTest {
    @Test
    public void getBarCode() {
        Book book = new Book("123", "A book", "An Author");
        assertEquals("123", book.getBarcode());
    }

    @Test
    public void getBookTitle() {
        Book book = new Book("456", "A book title", "Foo Bar");
        assertEquals("A book title", book.getTitle());
    }

    @Test
    public void getBookAuthor() {
        Book book = new Book("789", "Another title", "Bar Foo");
        assertEquals("Bar Foo", book.getAuthor());
    }

    @Test
    public void toStringCatch22() {
        Book book = new Book("123", "Catch-22", "Joseph Heller");
        assertEquals("[123] Catch-22 by Joseph Heller", book.toString());
    }
}
