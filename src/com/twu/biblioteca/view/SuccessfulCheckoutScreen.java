package com.twu.biblioteca.view;


public class SuccessfulCheckoutScreen implements Screen {
    public String printScreen() {
        return "Thank you! Enjoy the book.\n\nPress Return to view available books.";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass());
    }
}
