package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class BookCollectionTest {
    @Test
    public void testAvailableBooksJustOneBook () {
        Book book = new Book("12", "A", "Y Z");
        BookCollection collection = new BookCollection(
                new Book[]{book});
        assertArrayEquals(new Book[]{book}, collection.availableBooks());
    }

    @Test
    public void testAvailableBooksTwoBooks () {
        Book[] books = {
            new Book("12", "A", "Y Z"),
            new Book("23", "B", "W X")
        };
        BookCollection collection = new BookCollection(
                books);
        assertArrayEquals(books, collection.availableBooks());
    }
}
