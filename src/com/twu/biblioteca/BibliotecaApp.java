package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {

        Book[] books = {
                new Book("1", "Test-Driven Development By Example", "Kent Beck"),
                new Book("2", "Don't Make Me Think", "Steve Krug")
        };

        BookCollection collection = new BookCollection(books);

        System.out.println("Welcome to Biblioteca.");
        System.out.println(prettyAvailableBooks(collection.availableBooks()));
    }

    public static String prettyAvailableBooks(Book[] books) {
        if (books.length == 0) {
            return "No books available.";
        }

        StringBuilder pretty = new StringBuilder();
        pretty.append("Available books:");
        for (Book book : books) {
            pretty.append("\n  ").append(book.toString());
        }
        return pretty.toString();
    }
}
