package com.twu.biblioteca.controller;

import com.twu.biblioteca.view.Screen;

public interface AppState {
    AppState nextState(String input);
    Screen getScreen();
}
