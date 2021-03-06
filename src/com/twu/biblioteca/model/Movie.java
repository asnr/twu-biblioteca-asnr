package com.twu.biblioteca.model;


import java.time.Year;

public class Movie {

    private String barcode;
    private String title;
    private String director;
    private Year releaseYear;
    private int rating;

    private boolean checkedOut;

    private static final int NOT_RATED = -1;

    public Movie(String barcode, String title, String director,
                 Year releaseYear) {
        this(barcode, title, director, releaseYear, NOT_RATED);
    }

    public Movie(String barcode, String title, String director,
                 Year releaseYear, int rating) {
        this.barcode = barcode;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.checkedOut = false;
    }

    public String getBarcode() {
        return barcode;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append("[").append(barcode).append("] ").append(title)
                .append(" by ").append(director)
                .append(" released ").append(releaseYear.toString());

        if (rating == NOT_RATED) {
            str.append(" (not rated)");
        } else {
            str.append(" (rated ").append(rating).append("/10)");
        }

        return str.toString();
    }

    public boolean isAvailable() {
        return !checkedOut;
    }

    public Movie checkout() {
        checkedOut = true;
        return this;
    }

}
