package org.san.Books;

import org.san.Books.app.BookIdRecord;

import java.sql.SQLException;
import java.util.List;

public interface BookRepository {

    List<Book> getBookByTitle(String title) throws SQLException;

    List<Book> findBookByAuthor(Author author) throws SQLException;

    List<Book> getAllBooks();

    void insertBook(Book book) throws SQLException;

    void reserveBook(BookId bookid) throws SQLException;

    void cancelReservation(BookId id) throws SQLException;

    void borrowBook(BookId id) throws SQLException;

    void returnBook(BookId id) throws SQLException;
}
