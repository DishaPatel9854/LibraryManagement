package com.library.models;

import com.library.interfaces.ProgressObserver;
import com.library.interfaces.ProgressSubject;

import java.util.ArrayList;
import java.util.List;

public class Book implements ProgressSubject {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private User reservedBy;
    private boolean reserved;  // Field to track reservation status


    // New fields for tracking reading progress
    private int totalPages;
    private int pagesRead;
    private List<ProgressObserver> observers = new ArrayList<>();

    public Book(String title, String author, String isbn, int totalPages) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
        this.reservedBy = null;
        this.totalPages = totalPages;
        this.pagesRead = 0;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getTotalPages() {
        return totalPages;
    }
    public String getAuthor() {
        return author;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }


    // Methods to manage reading progress and observers
    public void setPagesRead(int pagesRead) {
        if (pagesRead > totalPages) {
            System.out.println("Pages read cannot exceed total pages.");
            return;
        }

        this.pagesRead = pagesRead;

        // Calculate percentage
        double progressPercentage = ((double) pagesRead / totalPages) * 100;

        System.out.printf("Reading progress updated for book: %s. You have completed %.2f%% of the book.%n", title, progressPercentage);

        notifyObservers("Reading progress updated for '" + title + "': " + pagesRead + "/" + totalPages);

        // Check for milestones
        checkMilestones();
    }


    private void checkMilestones() {
        if (pagesRead >= totalPages * 0.25 && pagesRead < totalPages * 0.5) {
            notifyObservers("You've read 25% of '" + title + "'!");
        } else if (pagesRead >= totalPages * 0.5 && pagesRead < totalPages * 0.75) {
            notifyObservers("You've read 50% of '" + title + "'!");
        } else if (pagesRead >= totalPages * 0.75 && pagesRead < totalPages) {
            notifyObservers("You've read 75% of '" + title + "'!");
        } else if (pagesRead == totalPages) {
            notifyObservers("Congratulations! You've completed reading '" + title + "'!");
        }
    }

    @Override
    public void registerObserver(ProgressObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ProgressObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (ProgressObserver observer : observers) {
            observer.update(message);
        }
    }
}
