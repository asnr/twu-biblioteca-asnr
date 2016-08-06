package com.twu.biblioteca.view;

import java.util.Objects;

public class QuitScreen implements Screen {
    public String printScreen() {
        return "Goodbye.\n";
    }

    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass());
    }
}
