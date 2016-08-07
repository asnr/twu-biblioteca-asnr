package com.twu.biblioteca;

import com.twu.biblioteca.controller.AppController;
import com.twu.biblioteca.model.NoSuchBookException;
import com.twu.biblioteca.view.*;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.BookCollection;
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
    public void defaultStartScreenIsMainMenu() {
        AppController controller = new AppController(emptyCollection);
        Screen screen = controller.getCurrentScreen();
        assertEquals(new MainMenuScreen(), screen);
    }

    @Test
    public void mainMenuSelectListBooksOption() {
        AppController controller = new AppController(sixBookCollection, AppController.State.MainMenu);
        Screen screen = controller.getNextScreen(new String("a"));
        assertEquals(new ListBooksScreen(books), screen);
    }

    @Test
    public void mainMenuSelectIncorrectInput() {
        AppController controller = new AppController(sixBookCollection, AppController.State.MainMenu);
        Screen screen = controller.getNextScreen("!!");
        assertEquals(new InvalidOptionScreen(), screen);
        screen = controller.getNextScreen("");
        assertEquals(new MainMenuScreen(), screen);
    }

    @Test
    public void mainMenuSelectQuit() {
        AppController controller = new AppController(sixBookCollection, AppController.State.MainMenu);
        Screen screen = controller.getNextScreen("q");
        assertEquals(new QuitScreen(), screen);
    }

    @Test
    public void listBooksReturnsToMainMenuOnEmptyInput() {
        AppController controller = new AppController(sixBookCollection, AppController.State.ListBooks);
        Screen screen = controller.getNextScreen("");
        assertEquals(new MainMenuScreen(), screen);
    }

    @Test
    public void correctlyCheckoutBookFromListBooks() {
        AppController controller = new AppController(sixBookCollection, AppController.State.ListBooks);
        Screen screen = controller.getNextScreen("1a");
        assertEquals(new SuccessfulCheckoutScreen(), screen);
    }

    @Test
    public void checkoutAlreadyCheckedOutBookFromListBooks() throws NoSuchBookException {
        AppController controller = new AppController(oneBookCollection, AppController.State.ListBooks);
        oneBookCollection.getBook("1a").checkout();
        Screen screen = controller.getNextScreen("1a");
        assertEquals(new UnsuccessfulCheckoutScreen(), screen);
    }

    @Test
    public void checkoutNonexistentBookFromListBooks() throws NoSuchBookException {
        AppController controller = new AppController(oneBookCollection, AppController.State.ListBooks);
        Screen screen = controller.getNextScreen("!!");
        assertEquals(new UnsuccessfulCheckoutScreen(), screen);
    }
}
