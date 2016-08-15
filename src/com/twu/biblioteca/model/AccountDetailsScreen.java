package com.twu.biblioteca.model;


import com.twu.biblioteca.controller.AccountDetailsState;
import com.twu.biblioteca.view.Screen;

public class AccountDetailsScreen implements Screen {

    private User user;

    public AccountDetailsScreen(User user) {
        this.user = user;
    }

    @Override
    public String printScreen() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nYour account details.")
                .append("\n  Name: ").append(user.getName())
                .append("\n  Email: ").append(user.getEmail())
                .append("\n  Phone number: ").append(user.getPhone())
                .append("\n\nPress Return to go back to the main menu.\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AccountDetailsScreen)) {
            return false;
        }

        AccountDetailsScreen screen = (AccountDetailsScreen) obj;
        return user == screen.user;
    }
}
