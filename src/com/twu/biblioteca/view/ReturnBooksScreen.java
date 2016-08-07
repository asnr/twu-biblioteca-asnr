package com.twu.biblioteca.view;

import com.twu.biblioteca.model.Book;

import java.util.Arrays;

public class ReturnBooksScreen implements Screen {

    public final static String screenEnd =
            "\n\nEnter the barcode of a book and then press Return to return it, "
                    + "otherwise just press Return to go back to the main menu.\n";

    private Book[] books;

    public ReturnBooksScreen(Book[] books) {
        this.books = books;
    }

    @Override
    public String printScreen() {
        if (books.length == 0) {
            return "\nNo books are currently checked out." + screenEnd;
        }

        StringBuilder toPrint = new StringBuilder();
        toPrint.append("\nBooks that are currently checked out:");
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

        ReturnBooksScreen screen = (ReturnBooksScreen) obj;
        return Arrays.equals(this.books, screen.books);
    }
}
