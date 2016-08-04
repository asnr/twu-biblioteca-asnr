package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;

public class AppControllerTest {

    private Book[] books;
    private BookCollection emptyCollection;
    private BookCollection oneBookCollection;
    private BookCollection sixBookCollection;

    @Before
    public void setUp() {
        this.books = new Book[] {
                new Book("1a", "The Hitchhiker's Guide To The Galaxy", "Douglas Adams", Year.of(1979)),
                new Book("1b", "The Hitchhiker's Guide To The Galaxy", "Douglas Adams", Year.of(1979)),
                new Book("2", "The Restaurant At The End Of The Universe", "Douglas Adams", Year.of(1980)),
                new Book("3", "Life, The Universe And Everything", "Douglas Adams", Year.of(1982)),
                new Book("4", "So Long, And Thanks For All The Fish", "Douglas Adams", Year.of(1984)),
                new Book("5", "Mostly Harmless", "Douglas Adams", Year.of(1992))
        };

        this.emptyCollection = new BookCollection(new Book[] {});
        this.oneBookCollection = new BookCollection(new Book[] {books[0]});
        this.sixBookCollection = new BookCollection(books);
    }

    @Test
    public void startScreenEmptyCollection() {
        AppController controller = new AppController(emptyCollection);
        Screen screen = controller.startScreen();
        assertEquals(new MainMenuScreen(), screen);
    }

    @Test
    public void processInputSelectListBooks() {
//        Arrays.copyOfRange(this.books, 0, 2)
        AppController controller = new AppController(sixBookCollection);
        Screen screen = controller.processInput("a");
        assertEquals(new ListBooksScreen(books), screen);
    }
}
