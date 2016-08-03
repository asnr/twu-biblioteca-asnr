package com.twu.biblioteca;

public class BookCollection {

    private Book[] books;

    public BookCollection(Book[] books) {
        this.books = books;
    }

    public Book[] availableBooks() {
        return books;
    }
}
