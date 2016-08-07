package com.twu.biblioteca.model;

import com.twu.biblioteca.model.Book;

import java.util.ArrayList;

public class BookCollection {

    private Book[] books;

    public BookCollection(Book[] books) {
        this.books = books;
    }

    public Book[] availableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks.toArray(new Book[availableBooks.size()]);
    }

    public Book[] checkedOutBooks() {
        ArrayList<Book> checkedOutBooks = new ArrayList<>();
        for (Book book : books) {
            if (!book.isAvailable()) {
                checkedOutBooks.add(book);
            }
        }
        return checkedOutBooks.toArray(new Book[checkedOutBooks.size()]);
    }

    public Book getBook(String barcode) throws NoSuchBookException {
        for (Book book : books) {
            if (book.getBarcode().equals(barcode)) {
                return book;
            }
        }

        throw new NoSuchBookException();
    }
}
