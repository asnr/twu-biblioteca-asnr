package com.twu.biblioteca;

import java.util.Arrays;

public class ListBooksScreen implements Screen {

    private Book[] books;

    public ListBooksScreen(Book[] books) {
        this.books = books;
    }

    public String printScreen() {
        if (books.length == 0) {
            return "\nNo books available.";
        }

        StringBuilder toPrint = new StringBuilder();
        toPrint.append("\nAvailable books:");
        for (Book book : books) {
            toPrint.append("\n  ").append(book.toString());
        }
        return toPrint.toString();
    }

    public boolean equals(Object obj) {
        ListBooksScreen screen = (ListBooksScreen) obj;
        return getClass().equals(obj.getClass())
                && Arrays.equals(this.books, screen.books);
    }
}
