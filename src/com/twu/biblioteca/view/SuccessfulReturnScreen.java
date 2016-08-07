package com.twu.biblioteca.view;

public class SuccessfulReturnScreen implements Screen {

    public String printScreen() {
        return "Thank you for returning the book.\n\n"
                + "Press Return to see other checked out books.\n\n";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass());
    }
}
