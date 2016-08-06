package com.twu.biblioteca.model;

import java.time.Year;

public class Book {

    private String barcode;
    private String title;
    private String author;
    private Year yearPublished;

    public Book(String barcode, String title, String author, Year yearPublished) {
        this.barcode = barcode;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Year getYearPublished() {
        return yearPublished;
    }

    @Override
    public String toString() {
        return "[" + this.barcode + "] " + title + " by " + author
                + " published " + yearPublished.toString();
    }
}
