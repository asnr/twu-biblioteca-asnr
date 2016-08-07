package com.twu.biblioteca;

import com.twu.biblioteca.controller.AppController;
import com.twu.biblioteca.view.QuitScreen;
import com.twu.biblioteca.view.Screen;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.BookCollection;

import java.io.PrintStream;
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

        runApp(consoleInput, System.out, controller);
    }


    public static void runApp(Scanner in, PrintStream out, AppController controller) {

        Screen currScreen = controller.startScreen();
        out.println(currScreen.printScreen());

        Screen lastScreen = new QuitScreen();

        while (!currScreen.equals(lastScreen)) {

            String userInput = in.nextLine();
            currScreen = controller.getNextScreen(userInput);
            out.println(currScreen.printScreen());

        }

    }
}
