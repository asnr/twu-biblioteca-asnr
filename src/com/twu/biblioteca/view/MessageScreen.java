package com.twu.biblioteca.view;


abstract public class MessageScreen implements Screen {

    abstract public String printScreen();

    @Override
    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass());
    }
}
