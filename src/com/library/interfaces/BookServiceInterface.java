package com.library.interfaces;

import com.library.models.Book;
import java.util.List;

public interface BookServiceInterface {
    void addBook(Book book);
    void deleteBook(String isbn);
    List<Book> searchBookByTitle(String title);  // Change return type to List<Book>
    List<Book> searchBooksByAuthor(String author);
    List<Book> getAllBooks();
}
