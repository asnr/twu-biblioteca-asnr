package com.twu.biblioteca.view;

public class UnsuccessfulCheckoutScreen implements Screen {

    public String printScreen() {
        return "That book is not available.\n\n"
                + "Press Return to view available books.\n\n";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass());
    }
}
