package com.twu.biblioteca.model;

import java.time.Year;

public class Book {

    private String barcode;
    private String title;
    private String author;
    private Year yearPublished;

    private boolean checkedOut;

    public Book(String barcode, String title, String author, Year yearPublished) {
        this.barcode = barcode;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.checkedOut = false;
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

    public Book checkout() {
        checkedOut = true;
        return this;
    }

    public Book checkin() {
        checkedOut = false;
        return this;
    }

    public boolean isAvailable() {
        return !checkedOut;
    }

    @Override
    public String toString() {
        return "[" + this.barcode + "] " + title + " by " + author
                + " published " + yearPublished.toString();
    }
}
