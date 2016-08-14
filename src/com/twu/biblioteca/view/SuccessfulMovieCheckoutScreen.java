package com.twu.biblioteca.view;


public class SuccessfulMovieCheckoutScreen extends MessageScreen {
    @Override
    public String printScreen() {
        return "Thank you! Enjoy the movie.\n\n"
                + "Press Return to view available movies.\n";
    }
}
