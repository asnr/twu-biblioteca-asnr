package com.twu.biblioteca;

public class AppController {

    BookCollection collection;

    public AppController(BookCollection collection) {
        this.collection = collection;
    }

    public Screen startScreen() {
        return new MainMenuScreen();
    }

    public Screen processInput(String input) {
        return new ListBooksScreen(collection.availableBooks());
    }
}
