package com.twu.biblioteca.view;

import com.twu.biblioteca.model.Book;

import java.util.Arrays;

public class ListBooksScreen implements Screen {

    public final static String noBooksMsg = "\nNo books available. "
            + "Press Return to go back to the main menu.\n";

    public final static String screenStart = "\nAvailable books:";

    public final static String screenEnd =
            "\n\nEnter the barcode of a book and then press Return to check it out, "
            + "otherwise just press Return to go back to the main menu.\n";

    private Book[] books;

    public ListBooksScreen(Book[] books) {
        this.books = books;
    }

    public String printScreen() {
        if (books.length == 0) {
            return noBooksMsg;
        }

        StringBuilder toPrint = new StringBuilder();
        toPrint.append(screenStart);
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
