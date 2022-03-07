package com.company;

public class BorrowingRequest {
    private int daysBorrowing;
    private String bookID;
    private boolean paid;

    public BorrowingRequest(int daysBorrowing, String bookID, boolean paid) {
        this.daysBorrowing = daysBorrowing;
        this.bookID = bookID;
        this.paid = paid;
    }

    // Getters and Setters
    public int getDaysBorrowing() {
        return daysBorrowing;
    }
    public void setDaysBorrowing(int borrowingPrice) {
        this.daysBorrowing = borrowingPrice;
    }
    public String getBookID() {
        return bookID;
    }
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    public boolean isPaid() {
        return paid;
    }
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
