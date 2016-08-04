package com.twu.biblioteca;


public class MainMenuScreen implements Screen {
    private String screen = "\n"
            +"Welcome to Biblioteca.\n"
            + "\n"
            + "Main menu:\n"
            + "  (a) list books\n"
            + "\n"
            + "Please select an option:\n";

    public String printScreen() {
        return screen;
    }

    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass());
    }
}
