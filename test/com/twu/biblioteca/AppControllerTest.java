package com.twu.biblioteca;

import com.twu.biblioteca.controller.AppController;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.NoSuchBookException;
import com.twu.biblioteca.view.*;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.BookCollection;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppControllerTest {

    private Book fstBook;
    private Book[] books;
    private BookCollection emptyBookCollection;
    private BookCollection oneBookCollection;
    private BookCollection sixBookCollection;

    private Movie fstMovie;
    private Movie[] emptyMovieCollection;
    private Movie[] singleMovieCollection;

    @Before
    public void setUp() {
        this.fstBook = new Book("1a", "The Hitchhiker's Guide To The Galaxy", "Douglas Adams", Year.of(1979));
        this.books = new Book[] {
                this.fstBook,
                new Book("1b", "The Hitchhiker's Guide To The Galaxy", "Douglas Adams", Year.of(1979)),
                new Book("2", "The Restaurant At The End Of The Universe", "Douglas Adams", Year.of(1980)),
                new Book("3", "Life, The Universe And Everything", "Douglas Adams", Year.of(1982)),
                new Book("4", "So Long, And Thanks For All The Fish", "Douglas Adams", Year.of(1984)),
                new Book("5", "Mostly Harmless", "Douglas Adams", Year.of(1992))
        };

        this.emptyBookCollection = new BookCollection(new Book[] {});
        this.oneBookCollection = new BookCollection(new Book[] {fstBook});
        this.sixBookCollection = new BookCollection(books);

        this.fstMovie = new Movie("M001", "A Movie", "A Director", Year.of(1978), 1);
        this.emptyMovieCollection = new Movie[] {};
        this.singleMovieCollection = new Movie[] {fstMovie};
    }

    @Test
    public void defaultStartScreenIsMainMenu() {
        AppController controller = new AppController(
                emptyBookCollection, emptyMovieCollection);
        Screen screen = controller.getCurrentScreen();
        assertEquals(new MainMenuScreen(), screen);
    }

    @Test
    public void mainMenuSelectListBooks() {
        AppController controller = new AppController(
                sixBookCollection, emptyMovieCollection,
                AppController.State.MainMenu);
        Screen screen = controller.getNextScreen(
                new String(MainMenuScreen.ListBooksOption));
        assertEquals(new ListBooksScreen(books), screen);
    }

    @Test
    public void mainMenuSelectReturnBooks() {
        AppController controller = new AppController(
                oneBookCollection, emptyMovieCollection,
                AppController.State.MainMenu);
        Screen screen = controller.getNextScreen(
                new String(MainMenuScreen.ReturnBooksOption));
        assertEquals(new ReturnBooksScreen(new Book[] {}), screen);
    }

    @Test
    public void mainMenuSelectListMovies() {
        AppController controller = new AppController(
                emptyBookCollection, emptyMovieCollection,
                AppController.State.MainMenu);
        Screen screen = controller.getNextScreen(
                new String(MainMenuScreen.ListMoviesOption));
        assertEquals(new ListMoviesScreen(new Movie[] {}), screen);
    }

    @Test
    public void mainMenuSelectIncorrectInput() {
        AppController controller = new AppController(sixBookCollection,
                emptyMovieCollection, AppController.State.MainMenu);
        Screen screen = controller.getNextScreen("!!!");
        assertEquals(new InvalidOptionScreen(), screen);
        screen = controller.getNextScreen("");
        assertEquals(new MainMenuScreen(), screen);
    }

    @Test
    public void mainMenuSelectQuit() {
        AppController controller = new AppController(sixBookCollection,
                emptyMovieCollection, AppController.State.MainMenu);
        Screen screen = controller.getNextScreen(
                new String(MainMenuScreen.QuitOption));
        assertEquals(new QuitScreen(), screen);
    }

    @Test
    public void listBooksReturnsToMainMenuOnEmptyInput() {
        AppController controller = new AppController(sixBookCollection,
                emptyMovieCollection, AppController.State.ListBooks);
        Screen screen = controller.getNextScreen("");
        assertEquals(new MainMenuScreen(), screen);
    }

    @Test
    public void correctlyCheckoutBookFromListBooks() {
        AppController controller = new AppController(sixBookCollection,
                emptyMovieCollection, AppController.State.ListBooks);
        Screen screen = controller.getNextScreen(fstBook.getBarcode());
        assertEquals(new SuccessfulCheckoutScreen(), screen);
    }

    @Test
    public void checkoutAlreadyCheckedOutBookFromListBooks(){
        AppController controller = new AppController(oneBookCollection,
                emptyMovieCollection, AppController.State.ListBooks);
        fstBook.checkout();
        Screen screen = controller.getNextScreen(fstBook.getBarcode());
        assertEquals(new UnsuccessfulCheckoutScreen(), screen);
    }

    @Test
    public void checkoutNonexistentBookFromListBooks() {
        AppController controller = new AppController(oneBookCollection,
                emptyMovieCollection, AppController.State.ListBooks);
        Screen screen = controller.getNextScreen("!!!");
        assertEquals(new UnsuccessfulCheckoutScreen(), screen);
    }

    @Test
    public void returnBooksReturnsToMainMenuOnEmptyInput() {
        AppController controller = new AppController(sixBookCollection,
                emptyMovieCollection, AppController.State.ReturnBooks);
        Screen screen = controller.getNextScreen("");
        assertEquals(new MainMenuScreen(), screen);
    }

    @Test
    public void returnCheckedOutBookFromReturnBooks() throws NoSuchBookException {
        AppController controller = new AppController(oneBookCollection,
                emptyMovieCollection, AppController.State.ReturnBooks);
        fstBook.checkout();
        Screen screen = controller.getNextScreen(fstBook.getBarcode());
        assertEquals(new SuccessfulReturnScreen(), screen);
    }

    @Test
    public void returnNonexistentBookFromReturnBooks() {
        AppController controller = new AppController(emptyBookCollection,
                emptyMovieCollection, AppController.State.ReturnBooks);
        Screen screen = controller.getNextScreen("!!!");
        assertEquals(new UnsuccessfulReturnScreen(), screen);
    }

    @Test
    public void returnAvailableBookFromReturnBooks() {
        AppController controller = new AppController(oneBookCollection,
                emptyMovieCollection, AppController.State.ReturnBooks);
        Screen screen = controller.getNextScreen(fstBook.getBarcode());
        assertEquals(new UnsuccessfulReturnScreen(), screen);
    }

    @Test
    public void listMoviesReturnToMainMenuOnEmptyInput() {
        AppController controller = new AppController(emptyBookCollection,
                emptyMovieCollection, AppController.State.ListMovies);
        Screen screen = controller.getNextScreen("");
        assertEquals(new MainMenuScreen(), screen);
    }
}
