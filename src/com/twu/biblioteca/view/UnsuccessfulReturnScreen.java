package com.twu.biblioteca.view;

public class UnsuccessfulReturnScreen extends MessageScreen {
    @Override
    public String printScreen() {
        return "That is not a valid book to return.\n\n"
                + "Press Return to view books that are currently checked out.\n\n";
    }
}
