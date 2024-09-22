package org.san.Books;

import java.sql.SQLException;
import java.util.List;

public interface BookRepository {

    List<Book> getBookByTitle(String title) throws SQLException;

    List<Book> findBookByAuthor(Author author) throws SQLException;

    List<Book> getAllBooks();

    List<Book> sortByTitle(List<Book> books);

    void insertBook(Book book) throws SQLException;
}
