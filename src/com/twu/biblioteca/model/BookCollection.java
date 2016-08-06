package com.twu.biblioteca.model;

import com.twu.biblioteca.model.Book;

public class BookCollection {

    private Book[] books;

    public BookCollection(Book[] books) {
        this.books = books;
    }

    public Book[] availableBooks() {
        return books;
    }
}
