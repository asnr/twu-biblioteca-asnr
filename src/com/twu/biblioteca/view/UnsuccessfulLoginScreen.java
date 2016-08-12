package com.twu.biblioteca.view;


public class UnsuccessfulLoginScreen extends MessageScreen {
    @Override
    public String printScreen() {
        return "Incorrect username and password combination. Press Return to try again.\n";
    }
}
