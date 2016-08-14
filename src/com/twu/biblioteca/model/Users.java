package com.twu.biblioteca.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Users {

    private User[] users;
    private User loggedInUser;

    private String loginLibraryNumber;

    public Users(User[] users) {
        this.users = users;
    }

    public Users(User[] users, User loggedInUser) {
        User[] allUsers = Arrays.copyOf(users, users.length + 1);
        allUsers[allUsers.length - 1] = loggedInUser;
        this.users = allUsers;
        this.loggedInUser = loggedInUser;
    }

    public void startLogin(String libraryNum) {
        loginLibraryNumber = libraryNum;
    }

    public boolean finishLogin(String password) {
        loggedInUser = null;
        for (User user : users) {
            if (user.loginMatches(loginLibraryNumber, password)) {
                loggedInUser = user;
                break;
            }
        }

        return loggedInUser != null;
    }

    public User loggedInUser() {
        return loggedInUser;
    }

}
