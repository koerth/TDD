package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BorrowingServiceTest {
    private BorrowingService borrowingService;
    private PaymentService paymentService;
    private Library library;
    private Book book;
    private LibrarySearchService librarySearchService;

    @BeforeEach
    void setup() {
        paymentService = spy(PaymentService.class);
        library = spy(Library.class);
        book = mock(Book.class);

        borrowingService = new BorrowingService(paymentService, library, book);
        librarySearchService = new LibrarySearchService(library);
    }

    @Test
    void should_CalculatePrice_When_GotBorrowingRequestFor5Days() {
        BorrowingRequest borrowingRequest = new BorrowingRequest(5, "2", true);

        int expected = 5 * 15;
        int actual = borrowingService.CalculatePrice(borrowingRequest);

        assertEquals(expected, actual);
    }

    @Test
    void should_CalculatePrice_When_Got2BorrowingRequestsFor7Days() {
        BorrowingRequest borrowingRequest = new BorrowingRequest(7, "5", true);
        BorrowingRequest borrowingRequest2 = new BorrowingRequest(7, "2", true);

        int expected = 7 * 15 + 7 * 15;
        int actual = borrowingService.CalculatePrice(borrowingRequest) + borrowingService.CalculatePrice(borrowingRequest2);

        assertEquals(expected, actual);
    }

    @Test
    void should_CountAllBooks() {
        when (library.getAvailableBooks()).thenReturn(library.availableBooks);

        int expected = 8;
        int actual = borrowingService.getBooks().getAvailableBooks().size();

        System.out.println("List: " + Arrays.toString(library.availableBooks.toArray()));
        System.out.println("String: " + library.getFirstAvailableBook());
        System.out.println("Int: " + library.countAvailableBooks());

        assertEquals(expected, actual);
    }

    @Test
    void should_ThrowException_when_ListEmpty() {
        when (library.getFirstAvailableBook()).thenThrow(IllegalStateException.class);

        Executable executable = () -> library.getFirstAvailableBook();

        assertThrows(IllegalStateException.class, executable);
    }

    @Test
    void should_ReturnTrue_when_IsAvailable() {
        Book hamlet = new Book("Hamlet", "William Shakespeare", "Shakespearean tragedy", true, null, null);
        assertTrue(hamlet.isAvailable());
    }

    @Test
    void should_BePaid_when_PaidRightAmount() {
        paymentService.pay(5, 5);

        assertTrue(paymentService.paid);
    }

    @Test
    void should_RemoveBook_when_Paid() {
        BorrowingRequest borrowingRequest = new BorrowingRequest(5, "5", true);
        Book hamlet = new Book("Hamlet", "William Shakespeare", "Shakespearean tragedy", true, null, null);
        borrowingService.borrowBook(hamlet, borrowingRequest);

        boolean expected = false;
        boolean actual = hamlet.isAvailable();

        assertEquals(expected, actual);
    }

    @Test
    void should_ThrowException_when_PaidTooLittle() {
        doThrow(new IllegalStateException()).when(paymentService).pay(anyInt());

        Executable executable = () -> paymentService.pay(37);

        assertThrows(IllegalStateException.class, executable);
    }

    @Test
    void should_CalculatePrice_when_2Requests() {
        BorrowingRequest borrowingRequest = new BorrowingRequest(2, "1", true);
        BorrowingRequest borrowingRequest2 = new BorrowingRequest(5, "1", true);

        int expected = 5 * 15 + 2 * 15;
        int actual = borrowingService.CalculatePrice(borrowingRequest) + borrowingService.CalculatePrice(borrowingRequest2);

        assertEquals(expected, actual);
    }

    @Test
    void should_AddComment() {
        Book cppForDummies = new Book("C++ For Dummies", "Stephen Randy Davis", "Programming", true, null, null);
        String comment = "This book is very good for beginners, it teaches you all the basics you need to start programming in C++!";

        borrowingService.addComment(cppForDummies, comment);

        ArrayList<String> expected = new ArrayList<>(cppForDummies.getComments());
        ArrayList<String> actual = new ArrayList<>();

        actual.add(comment);

        assertEquals(expected, actual);
    }

    @Test
    void should_AddRating() {
        Book javaHeadFirst = new Book("Head First Java, 2nd Edition", "Kathy Sierra", "Programming", true, null, null);
        double rating = 4.5;

        borrowingService.addRating(javaHeadFirst, rating);

        ArrayList<Double> expected = new ArrayList<>(javaHeadFirst.getRatings());
        ArrayList<Double> actual = new ArrayList<>();

        actual.add(rating);

        assertEquals(expected, actual);
    }
}