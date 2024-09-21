package org.san.Books.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.san.Books.Author;
import org.san.Books.Book;
import org.san.Books.BookId;
import org.san.Books.BookRepository;
import javax.sql.DataSource;
import java.sql.*;
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
    public List<Book> findBookByAuthor(Author author) throws SQLException {

        List<Book> books = new ArrayList<>();

        String sql = "SELECT id, title, authorName, authorSurname, year FROM Books " +
                "WHERE authorName = ? AND authorSurname = ?";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,author.authorName() );
            preparedStatement.setString(2,author.authorSurname());
        }

        return List.of();
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, title, authorName, authorSurname, year FROM Books")) {

            while (resultSet.next()) {
                      BookId bookId = new BookIdRecord(resultSet.getString("id"));
                      String title = resultSet.getString("title");
                      Author author = new AuthorRecord(resultSet.getString("authorName"),resultSet.getString("authorSurname"));
                      int year = resultSet.getInt("year");
                      Book book = new BookRecord(bookId, title, author, year);
                      books.add(book);
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


