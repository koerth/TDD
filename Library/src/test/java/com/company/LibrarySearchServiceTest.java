package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LibrarySearchServiceTest {
    private LibrarySearchService librarySearchService;
    private Library library;

    private Book nineteenEightyFour = new Book("1984", "George Orwell", "Dystopia", true, null, null);
    private Book divineComedy = new Book("Divine Comedy", "Dante Alighieri", "Epic poetry", true, null, null);
    private Book hamlet = new Book("Hamlet", "William Shakespeare", "Shakespearean tragedy", true, null, null);
    private Book odyssey = new Book("Odyssey", "Homer", "Epic poetry", false, null, null);
    private Book headFirstJava = new Book("Head First Java, 2nd Edition", "Kathy Sierra", "Programming", true, null, null);
    private Book cppForDummies = new Book("C++ For Dummies", "Stephen Randy Davis", "Programming", true, null, null);
    private Book donQuixote = new Book("Don Quixote", "Miguel de Cervantes", "Satire", true, null, null);
    private Book ulysses = new Book("Ulysses", "James Joyce", "Modernist novel", false, null, null);

    private ArrayList<Book> availableBooks;

    @BeforeEach
    void setup() {
        library = mock(Library.class);

        librarySearchService = new LibrarySearchService(library);

        availableBooks = new ArrayList<Book>();

        availableBooks.add(nineteenEightyFour);
        availableBooks.add(divineComedy);
        availableBooks.add(hamlet);
        availableBooks.add(odyssey);
        availableBooks.add(headFirstJava);
        availableBooks.add(cppForDummies);
        availableBooks.add(donQuixote);
        availableBooks.add(ulysses);
    }

    @Test
    void should_ReturnBookByAuthor_when_AuthorInInput() {
        when(library.getAvailableBooks()).thenReturn(availableBooks);

        HashSet<Book> expected = new HashSet<>();
        expected.add(headFirstJava);

        HashSet<Book> actual = librarySearchService.getResultFromInput("Kathy");

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnEmpty_when_NoAuthorFound() {
        when(library.getAvailableBooks()).thenReturn(availableBooks);

        HashSet<Book> expected = new HashSet<>();

        HashSet<Book> actual = librarySearchService.getResultFromInput("Cristiano Ronaldo");

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnBooksMatchingGenre_when_GenreInInput() {
        when(library.getAvailableBooks()).thenReturn(availableBooks);

        HashSet<Book> expected = new HashSet<>();
        expected.add(headFirstJava);
        expected.add(cppForDummies);

        HashSet<Book> actual = librarySearchService.getResultFromInput("Programming");

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnEmptyHashSet_when_InputIsLessThan2Characters() {
        when(library.getAvailableBooks()).thenReturn(availableBooks);

        HashSet<Book> expected = new HashSet<>();

        HashSet<Book> actual = librarySearchService.getResultFromInput("h");

        assertEquals(expected, actual);
    }

    @Test
    void should_TrimInputAndShowCorrectBooks_when_ExtraSpaceInInput() {
        when(library.getAvailableBooks()).thenReturn(availableBooks);

        HashSet<Book> expected = new HashSet<>();
        expected.add(headFirstJava);

        HashSet<Book> actual = librarySearchService.getResultFromInput("       Head");

        assertEquals(expected, actual);

    }
}