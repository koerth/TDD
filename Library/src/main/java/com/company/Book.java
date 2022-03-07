package com.company;

import java.util.ArrayList;

// Class for books
public class Book {
    private String name;
    private String author;
    private String genre;
    boolean available;
    private ArrayList<String> comments;
    private ArrayList<Double> ratings;

    // Including arrayLists- and var parameters
    public Book(String name, String author, String genre, boolean available, ArrayList<String> comments, ArrayList<Double> ratings) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.available = available;
        this.comments = comments;
        this.ratings = ratings;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public ArrayList<String> getComments() {
        return comments;
    }
    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }
    public ArrayList<Double> getRatings() {
        return ratings;
    }
    public void setRatings(ArrayList<Double> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.author + " - " + this.genre + " - " + this.available + " - " + this.comments + " - " + this.ratings;
    }
}

