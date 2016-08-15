package com.twu.biblioteca.view;

public class UnsuccessfulCheckoutScreen extends MessageScreen {

    public String printScreen() {
        return "That book is not available.\n\n"
                + "Press Return to view available books.\n";
    }

}
