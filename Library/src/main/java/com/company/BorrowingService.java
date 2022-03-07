package com.company;

import java.util.ArrayList;

public class BorrowingService {
    private PaymentService paymentService;
    private Library library;
    private Book book;

    // Setting price per day
    public static int bookPricePerDay = 15;

    public BorrowingService(PaymentService paymentService, Library library, Book book) {
        this.paymentService = paymentService;
        this.library = library;
        this.book = book;
    }

    public Library getBooks() {
        return library;
    }

    // Calculated price, based on number of borrowed days
    public int CalculatePrice(BorrowingRequest borrowingRequest) {
        return borrowingRequest.getDaysBorrowing() * bookPricePerDay;
    }

    // Checking if book is available
    public void borrowBook(Book book, BorrowingRequest borrowingRequest) {
        if (book.isAvailable() && borrowingRequest.isPaid()) {
            book.setAvailable(false); // Sets to unavailable 
        }
    }

    // Comment
    public void addComment(Book book, String comment) {
        ArrayList<String> comments = new ArrayList<>();

        comments.add(comment);
        book.setComments(comments);
    }

    // Rating
    public void addRating(Book book, double rating) {
        ArrayList<Double> ratings = new ArrayList<>();

        ratings.add(rating);
        book.setRatings(ratings);
    }

    
}
