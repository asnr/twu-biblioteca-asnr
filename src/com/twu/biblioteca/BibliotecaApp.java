package com.twu.biblioteca;

import java.time.Year;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {

        Book[] books = {
                new Book("1", "Test-Driven Development By Example", "Kent Beck", Year.of(2003)),
                new Book("2", "Don't Make Me Think", "Steve Krug", Year.of(2000))
        };

        BookCollection collection = new BookCollection(books);
        AppController controller = new AppController(collection);

        Scanner consoleInput = new Scanner(System.in);

        System.out.println(controller.startScreen().printScreen());
        consoleInput.nextLine();
        System.out.println(controller.processInput("a").printScreen());
    }
}
