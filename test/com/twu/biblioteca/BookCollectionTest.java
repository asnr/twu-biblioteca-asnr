package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.BookCollection;

import java.time.Year;

import com.twu.biblioteca.model.NoSuchBookException;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class BookCollectionTest {

    @Test
    public void testAvailableBooksJustOneBook () {
        Book book = new Book("12", "A", "Y Z", Year.of(2000));
        BookCollection collection = new BookCollection(
                new Book[]{book});
        assertArrayEquals(new Book[]{book}, collection.availableBooks());
    }

    @Test
    public void availableBooksEmptyWhenOnlyBookIsAlreadyCheckedOut () {
        Book checkedOutBook = new Book("12", "A", "Y Z", Year.of(2000));
        checkedOutBook.checkout();
        BookCollection collection = new BookCollection(
                new Book[] {checkedOutBook});
        assertArrayEquals(new Book[] {}, collection.availableBooks());

    }

    @Test
    public void testAvailableBooksTwoBooks () {
        Book[] books = {
            new Book("12", "A", "Y Z", Year.of(2001)),
            new Book("23", "B", "W X", Year.of(1901))
        };
        BookCollection collection = new BookCollection(
                books);
        assertArrayEquals(books, collection.availableBooks());
    }

    @Test(expected = NoSuchBookException.class)
    public void getBookThatDoesntExistThrowsException() throws NoSuchBookException {
        BookCollection collection = new BookCollection(new Book[] {});
        collection.getBook("123");
    }

    @Test
    public void getBookThatDoesExist() throws  NoSuchBookException{
        Book book = new Book("12", "A", "Y Z", Year.of(2001));
        BookCollection collection = new BookCollection(new Book[] {book});
        assertEquals(book, collection.getBook("12"));
    }

}
