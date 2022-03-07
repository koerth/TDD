package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {
    ArrayList<Book> availableBooks = new ArrayList<>();

    // Library of books
    public Library() {
        availableBooks.add(new Book("1984", "George Orwell", "Dystopia", true, null, null));
        availableBooks.add(new Book("Divine Comedy", "Dante Alighieri", "Epic poetry", true, null, null));
        availableBooks.add(new Book("Hamlet", "William Shakespeare", "Shakespearean tragedy", true, null, null));
        availableBooks.add(new Book("Don Quixote", "Miguel de Cervantes", "Satire", true, null, null));
        availableBooks.add(new Book("Ulysses", "James Joyce", "Modernist novel", false, null, null));
        availableBooks.add(new Book("TDD", "Gritacademy", "Programming", true, null, null));
        availableBooks.add(new Book("Kulturvetenskap", "Malmohogskola", "Swedish Lecturing", true, null, null));
        availableBooks.add(new Book("Math for Dummies", "Dummies", "Mathematics", true, null, null));
        availableBooks.add(new Book("Odyssey", "Homer", "Epic poetry", false, null, null));
        availableBooks.add(new Book("Head First Java, 2nd Edition", "Kathy Sierra", "Programming", true, null, null));
        availableBooks.add(new Book("C++ For Dummies", "Stephen Randy Davis", "Programming", true, null, null));
    }

    // Gets all available books
    public ArrayList<Book> getAvailableBooks() {
        return availableBooks;
    }

    // Gets first book and returns it as string
    public String getFirstAvailableBook() {
        if (countAvailableBooks() == 0) {
            throw new IllegalStateException();
        }
        Book firstBook = availableBooks.get(0);
        availableBooks.remove(firstBook);
        return firstBook.toString();
    }

    // Checks the size of the array (length of array), returning amount of books
    public int countAvailableBooks() {
        return availableBooks.size();
    }
}
