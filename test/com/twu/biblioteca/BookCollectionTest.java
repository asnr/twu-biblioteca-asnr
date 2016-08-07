package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.BookCollection;

import java.time.Year;

import com.twu.biblioteca.model.NoSuchBookException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class BookCollectionTest {

    private Book chronicle;

    @Before
    public void setUp() {
        this.chronicle = new Book("M01", "The Chronicle of a Death Foretold",
                "Marquez", Year.of(1981));
    }

    @Test
    public void testAvailableBooksJustOneBook () {
        BookCollection collection = new BookCollection(
                new Book[]{chronicle});
        assertArrayEquals(new Book[]{chronicle}, collection.availableBooks());
    }

    @Test
    public void availableBooksEmptyWhenOnlyBookIsAlreadyCheckedOut () {
        chronicle.checkout();
        BookCollection collection = new BookCollection(
                new Book[] {chronicle});
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
        BookCollection collection = new BookCollection(new Book[] {chronicle});
        assertEquals(chronicle, collection.getBook(chronicle.getBarcode()));
    }

    @Test
    public void checkedOutBooksJustOneCheckedOutBook() {
        chronicle.checkout();
        BookCollection collection = new BookCollection(new Book[] {chronicle});
        assertArrayEquals(new Book[] {chronicle}, collection.checkedOutBooks());
    }

    @Test
    public void checkedOutBooksNoBooksCheckedOut() {
        BookCollection collection = new BookCollection(new Book[] {chronicle});
        assertArrayEquals(new Book[] {}, collection.checkedOutBooks());
    }
}
