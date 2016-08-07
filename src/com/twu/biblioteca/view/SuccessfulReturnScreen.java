package com.twu.biblioteca.view;

public class SuccessfulReturnScreen extends MessageScreen {

    public String printScreen() {
        return "Thank you for returning the book.\n\n"
                + "Press Return to see other checked out books.\n\n";
    }
}
