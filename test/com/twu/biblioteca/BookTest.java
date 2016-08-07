package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookTest {

    private Book catch22;

    @Before
    public void setUp() {
        this.catch22 = new Book("0000", "Catch-22", "Joseph Heller", Year.of(1961));
    }

    @Test
    public void getBarCode() {
        assertEquals("0000", catch22.getBarcode());
    }

    @Test
    public void getBookTitle() {
        assertEquals("Catch-22", catch22.getTitle());
    }

    @Test
    public void getBookAuthor() {
        assertEquals("Joseph Heller", catch22.getAuthor());
    }

    @Test
    public void toStringCatch22() {
        assertEquals("[0000] Catch-22 by Joseph Heller published 1961",
                catch22.toString());
    }

    @Test
    public void newBookIsAvailable() {
        assertTrue(catch22.isAvailable());
    }

    @Test
    public void checkedOutBookIsNotAvailable() {
        catch22.checkout();
        assertFalse(catch22.isAvailable());
    }

    @Test
    public void returnedBookIsAvailable() {
        catch22.checkout().checkin();
        assertTrue(catch22.isAvailable());
    }
    
}
