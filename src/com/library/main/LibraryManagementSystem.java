package com.library.main;

import com.library.models.Book;
import com.library.models.User;
import com.library.services.BookService;
import com.library.services.UserService;

import java.util.Scanner;

public class LibraryManagementSystem {
    private static UserService userService = new UserService();
    private static BookService bookService = new BookService();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        System.out.println("Welcome to the Library Management System!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (loggedInUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();

                scanner.nextLine(); // consume newline
                switch (option) {
                    case 1:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        userService.registerUser(username, password);
                        break;
                    case 2:
                        System.out.print("Enter username: ");
                        String loginUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String loginPassword = scanner.nextLine();
                        loggedInUser = userService.loginUser(loginUsername, loginPassword);
                        break;
                }
            } else {
                System.out.println("1. Add a book to your personal library");
                System.out.println("2. Delete book from your personal library");
                System.out.println("3. Search Book by Title");
                System.out.println("4. Search Books by Author");
                System.out.println("5. Reserve a Book");
                System.out.println("6. Release a Book Reservation");
                System.out.println("7. Register for Book Progress Updates");
                System.out.println("8. Update Reading Progress");
                System.out.println("9. List All Books");
                System.out.println("10. Logout");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();

                scanner.nextLine(); // consume newline
                switch (option) {
                    case 1:
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Enter total pages: ");
                        int totalPages = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        bookService.addBook(new Book(title, author, isbn, totalPages));
                        break;
                    case 2:
                        System.out.print("Enter ISBN: ");
                        String deleteIsbn = scanner.nextLine();
                        bookService.deleteBook(deleteIsbn);
                        break;
                    case 3:
                        System.out.print("Enter title: ");
                        String searchTitle = scanner.nextLine();
                        Book book = bookService.searchBookByTitle(searchTitle);
                        System.out.println(book != null ? book.getTitle() + " by " + book.getAuthor() : "Book not found!");
                        break;
                    case 4:
                        System.out.print("Enter author: ");
                        String searchAuthor = scanner.nextLine();
                        bookService.searchBooksByAuthor(searchAuthor).forEach(b ->
                                System.out.println(b.getTitle() + " by " + b.getAuthor())
                        );
                        break;
                    case 5:
                        System.out.print("Enter ISBN: ");
                        String reserveIsbn = scanner.nextLine();
                        if (loggedInUser != null) {
                            bookService.reserveBook(reserveIsbn, loggedInUser);
                        }
                        break;
                    case 6:
                        System.out.print("Enter ISBN: ");
                        String releaseIsbn = scanner.nextLine();
                        if (loggedInUser != null) {
                            bookService.releaseBookReservation(releaseIsbn, loggedInUser);
                        }
                        break;
                    case 7:
                        System.out.print("Enter ISBN: ");
                        String progressIsbn = scanner.nextLine();
                        if (loggedInUser != null) {
                            bookService.registerUserForBookProgress(progressIsbn, loggedInUser);
                        }
                        break;
                    case 8:
                        System.out.print("Enter ISBN: ");
                        String updateIsbn = scanner.nextLine();
                        System.out.print("Enter pages read: ");
                        int pagesRead = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        bookService.updateReadingProgress(updateIsbn, pagesRead);
                        break;
                    case 9:
                        bookService.getAllBooks().forEach(b ->
                                System.out.println(b.getTitle() + " by " + b.getAuthor())
                        );
                        break;
                    case 10:
                        loggedInUser = null;
                        System.out.println("Logged out successfully!");
                        break;
                }
            }
        }
    }
}
