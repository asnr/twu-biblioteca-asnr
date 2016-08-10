package com.twu.biblioteca.view;


public class MainMenuScreen implements Screen {

    public final static String ListBooksOption = "a";
    public final static String ReturnBooksOption = "b";
    public final static String ListMoviesOption = "c";
    public final static String QuitOption = "q";

    private final String screen = "\n"
            +"Welcome to Biblioteca.\n"
            + "\n"
            + "Main menu:\n"
            + "  (a) List books\n"
            + "  (b) Return books\n"
            + "  (c) List movies\n"
            + "  (q) Quit\n"
            + "\n"
            + "Please select an option:\n";

    public String printScreen() {
        return screen;
    }

    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass());
    }
}
