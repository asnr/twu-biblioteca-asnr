package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {
    @Test
    public void prettyAvailableBooksNoBooks() {
        String pretty = BibliotecaApp.prettyAvailableBooks(new Book[] {});
        assertEquals("No books available.", pretty);
    }

    @Test
    public void prettyAvailableBooksOneBook() {
        Book book = new Book("123", "Foo", "Ms. Bar");
        String pretty = BibliotecaApp.prettyAvailableBooks(new Book[] {book});
        assertEquals("Available books:\n  " + book.toString(), pretty);
    }

    @Test
    public void prettyAvailableBooksTwoBooks() {
        Book book1 = new Book("101", "Foo", "Ms. Bar");
        Book book2 = new Book("102", "Bar", "Mr. Foo");
        String pretty = BibliotecaApp.prettyAvailableBooks(new Book[] {book1, book2});
        assertEquals("Available books:\n  " + book1.toString() + "\n  " + book2.toString(),
                pretty);
    }

}
