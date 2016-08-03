package com.twu.biblioteca;


import com.sun.xml.internal.ws.api.ha.StickyFeature;

public class Book {

    private String barcode;
    private String title;
    private String author;

    public Book(String barcode, String title, String author) {
        this.barcode = barcode;
        this.title = title;
        this.author = author;
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

    @Override
    public String toString() {
        return "[" + this.barcode + "] " + title + " by " + author;
    }
}
