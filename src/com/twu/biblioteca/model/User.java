package com.twu.biblioteca.model;


import java.util.ArrayList;

public class User {

    private String libraryNum;
    private String password;
    private String name;
    private String email;
    private String phone;

    private ArrayList<Book> checkedOutBooks;

    public User(String libraryNum, String password, String name, String email, String phone) {
        this.libraryNum = libraryNum;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;

        this.checkedOutBooks = new ArrayList<>();
    }

    public String getLibraryNum() {
        return libraryNum;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean loginMatches(String libraryNum, String password) {
        return libraryNum.equals(this.getLibraryNum())
                && password.equals(this.password);
    }

    public void checkout(Book book) {
        book.checkout();
        checkedOutBooks.add(book);
    }

    public boolean doesHold(Book book) {
        return checkedOutBooks.contains(book);
    }

    public void checkin(Book book) {
        checkedOutBooks.remove(book);
        book.checkin();
    }

    public Book[] checkedOutBooks() {
        return checkedOutBooks.toArray(new Book[checkedOutBooks.size()]);
    }

}
