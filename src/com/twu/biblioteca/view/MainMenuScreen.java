package com.twu.biblioteca.view;


public class MainMenuScreen implements Screen {
    private String screen = "\n"
            +"Welcome to Biblioteca.\n"
            + "\n"
            + "Main menu:\n"
            + "  (a) List books\n"
            + "  (b) Return books\n"
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
