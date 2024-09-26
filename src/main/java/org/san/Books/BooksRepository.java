package org.san.Books;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BooksRepository {

    List<Book> getBookByTitle(String title) throws SQLException;

    List<Book> findBookByAuthor(Author author) throws SQLException;

    Optional<Book> findBookById(BookId id) throws SQLException;

    List<Book> getAllBooks() throws SQLException;

    void insertBook(Book book) throws SQLException;

    void reserveBook(BookId bookid) throws SQLException;

    void cancelReservation(BookId id) throws SQLException;

    void borrowBook(BookId id) throws SQLException;

    void returnBook(BookId id) throws SQLException;

}
