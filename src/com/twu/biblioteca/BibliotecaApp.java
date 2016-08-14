package com.twu.biblioteca;

import com.twu.biblioteca.controller.AppState;
import com.twu.biblioteca.controller.LoginState;
import com.twu.biblioteca.model.*;
import com.twu.biblioteca.view.QuitScreen;
import com.twu.biblioteca.view.Screen;

import java.io.PrintStream;
import java.time.Year;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {

        Users users = new Users(new User[] {
            new User("000-0001", "password", "A Name", "a@email.com", "1234 5678"),
            new User("000-0002", "password", "Another Name", "b@email.com", "2345 6789")});

        Book[] books = {
                new Book("1", "Test-Driven Development By Example", "Kent Beck", Year.of(2003)),
                new Book("2", "Don't Make Me Think", "Steve Krug", Year.of(2000))
        };

        Movie[] movies = {
                new Movie("M001", "A Movie", "A Director", Year.of(1978), 1)
        };

        BookCollection bookCollection = new BookCollection(books);
        MovieCollection movieCollection = new MovieCollection(movies);

        Scanner consoleInput = new Scanner(System.in);

        runApp(consoleInput, System.out, users, bookCollection, movieCollection);
    }


    public static void runApp(Scanner in, PrintStream out,
                              Users users, BookCollection books,
                              MovieCollection movies) {

        AppState state = new LoginState(users, books, movies);

        Screen currScreen = state.getScreen();
        out.print(currScreen.printScreen());

        Screen lastScreen = new QuitScreen();

        while (!currScreen.equals(lastScreen)) {

            String userInput = in.nextLine();
            state = state.nextState(userInput);
            currScreen = state.getScreen();
            out.print(currScreen.printScreen());

        }

    }
}
