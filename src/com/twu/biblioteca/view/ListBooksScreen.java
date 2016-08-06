package com.twu.biblioteca.view;

import com.twu.biblioteca.model.Book;

import java.util.Arrays;

public class ListBooksScreen implements Screen {

    public final static String screenEnd = "\n\n Press Enter to return to the main menu.\n";

    private Book[] books;

    public ListBooksScreen(Book[] books) {
        this.books = books;
    }

    public String printScreen() {
        if (books.length == 0) {
            return "\nNo books available." + screenEnd;
        }

        StringBuilder toPrint = new StringBuilder();
        toPrint.append("\nAvailable books:");
        for (Book book : books) {
            toPrint.append("\n  ").append(book.toString());
        }
        toPrint.append(screenEnd);
        return toPrint.toString();
    }

    public boolean equals(Object obj) {
        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        ListBooksScreen screen = (ListBooksScreen) obj;
        return Arrays.equals(this.books, screen.books);
    }
}
