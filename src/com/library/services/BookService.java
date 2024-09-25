package com.library.services;

import com.library.models.Book;
import com.library.models.User;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private List<Book> books = new ArrayList<>();


    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    // Delete a book by ISBN
    public void deleteBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        System.out.println("Book deleted with ISBN: " + isbn);
    }

    // Search for a book by title
    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Search for books by author
    public List<Book> searchBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    // Reserve a book for a user
    public void reserveBook(String isbn, User user) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.isReserved()) {
                    book.setReserved(true);
                    System.out.println(user.getUsername() + " reserved the book: " + book.getTitle());
                } else {
                    System.out.println("Book is already reserved.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Release a book reservation
    public void releaseBookReservation(String isbn, User user) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isReserved()) {
                    book.setReserved(false);
                    System.out.println(user.getUsername() + " released the reservation for: " + book.getTitle());
                } else {
                    System.out.println("Book is not reserved.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }


    public void updateReadingProgress(String isbn, int pagesRead) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.setPagesRead(pagesRead);
                System.out.println("Reading progress updated for book: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book not found.");
    }


    public void registerUserForBookProgress(String isbn, User user) {
        System.out.println(user.getUsername() + " is now registered for progress updates on book with ISBN: " + isbn);
    }


    public List<Book> getAllBooks() {
        return books;
    }
}
