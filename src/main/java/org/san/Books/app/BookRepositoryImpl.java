package org.san.Books.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.san.Books.Author;
import org.san.Books.Book;
import org.san.Books.BookRepository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookRepositoryImpl implements BookRepository {
    @Inject
    DataSource dataSource;
    @Override
    public List<Book> getBookByTitle(String title) {
        return List.of();
    }

    @Override
    public List<Book> findBookByAuthor(Author author) {
        return List.of();
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, title, authorName, authorSurname, year FROM Books")) {

            while (resultSet.next()) {
                      String bookId = resultSet.getString("bookId");
                      String title = resultSet.getString("title");
                      String authorName = resultSet.getString("authorName");
                      String authorSurname =  resultSet.getString("authorSurname");
                      int year = resultSet.getInt("year");
                      Book book = new BookRecord(bookId, title, authorName, authorSurname, year);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
    @Override
    public List<Book> sortByTitle(List<Book> books) {
        return List.of();
    }
    }


