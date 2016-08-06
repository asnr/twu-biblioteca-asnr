package com.twu.biblioteca.view;


public class InvalidOptionScreen implements Screen {
    public String printScreen() {
        return "\nInvalid Option!\n\nPress Enter to continue.\n";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass());
    }
}
