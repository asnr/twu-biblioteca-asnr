package com.twu.biblioteca;

import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;

public class ListBooksScreenTest {
    @Test
    public void printScreenNoBooks() {
        Screen screen = new ListBooksScreen(new Book[] {});
        assertEquals("\nNo books available.", screen.printScreen());
    }

    @Test
    public void printScreenOneBook() {
        Book book = new Book("123", "Foo", "Ms. Bar", Year.of(1999));
        Screen screen = new ListBooksScreen(new Book[] {book});
        assertEquals("\nAvailable books:\n  " + book.toString(), screen.printScreen());
    }

    @Test
    public void printScreenTwoBooks() {
        Book book1 = new Book("101", "Foo", "Ms. Bar", Year.of(1500));
        Book book2 = new Book("102", "Bar", "Mr. Foo", Year.of(1600));
        Screen screen = new ListBooksScreen(new Book[] {book1, book2});
        assertEquals("\nAvailable books:\n  " + book1.toString() + "\n  " + book2.toString(),
                screen.printScreen());
    }
}
