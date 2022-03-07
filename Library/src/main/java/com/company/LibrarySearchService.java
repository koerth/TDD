package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class LibrarySearchService {
    private Library library;

    public LibrarySearchService(Library library) {
        this.library = library;
    }

    // Gets result as array
    public HashSet<Book> getResultFromInput(String input) {
        ArrayList<Book> availableBooks = library.getAvailableBooks();
        HashSet<Book> searchList = new HashSet<>(); // Unique

        input = input.trim();

        if (input.length() > 1) {
            for(Book book : availableBooks) {
                    if (book.getName() != null && book.getName().contains(input) || book.getAuthor() != null && book.getAuthor().contains(input) || book.getGenre() != null && book.getGenre().contains(input)) {
                        searchList.add(book);
                    }
                }
            }
        return searchList;
    }

    // Gets rating
    public HashSet<Book> getHighestRatedBooks() {
        ArrayList<Book> availableBooks = library.getAvailableBooks();
        HashSet<Book> searchList = new HashSet<>(); // Unique

        double finalRating = 0;

        for (Book book : availableBooks) {
            if (book.getRatings() != null) {
                for (int i = 0; i < book.getRatings().size(); i++) {
                    finalRating += book.getRatings().get(i);
                }

                finalRating = finalRating / book.getRatings().size();
                if (finalRating == 5) {
                    searchList.add(book);
                }
            }
        }
        return searchList;
    }
}
