package com.twu.biblioteca;

import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {
    @Test
    public void prettyAvailableBooksNoBooks() {
        String pretty = BibliotecaApp.prettyAvailableBooks(new Book[] {});
        assertEquals("No books available.", pretty);
    }

    @Test
    public void prettyAvailableBooksOneBook() {
        Book book = new Book("123", "Foo", "Ms. Bar", Year.of(1999));
        String pretty = BibliotecaApp.prettyAvailableBooks(new Book[] {book});
        assertEquals("Available books:\n  " + book.toString(), pretty);
    }

    @Test
    public void prettyAvailableBooksTwoBooks() {
        Book book1 = new Book("101", "Foo", "Ms. Bar", Year.of(1500));
        Book book2 = new Book("102", "Bar", "Mr. Foo", Year.of(1600));
        String pretty = BibliotecaApp.prettyAvailableBooks(new Book[] {book1, book2});
        assertEquals("Available books:\n  " + book1.toString() + "\n  " + book2.toString(),
                pretty);
    }

}
