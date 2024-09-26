package org.san.Books.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.san.Books.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
class BookRepositoryImpl implements BookRepository {

    @Inject
    DataSource dataSource;

    @Inject
    BookFacade bookFacade;

    private Book mapFieldsToValue(ResultSet resultSet) throws SQLException {

        BookId bookId = new BookIdRecord(UUID.fromString(resultSet.getString("id")));
        String title = resultSet.getString("title");
        Author author = new AuthorRecord(resultSet.getString("authorName"),resultSet.getString("authorSurname"));
        int year = resultSet.getInt("year");
        boolean reserved = resultSet.getBoolean("reserved");
        boolean borrowed = resultSet.getBoolean("borrowed");

        return new BookRecord(bookId, title, author, year, reserved, borrowed);
    }

    @Override
    public List<Book> getBookByTitle(String title) throws SQLException {

        List<Book> books = new ArrayList<>();

        String sql = "SELECT id, title, authorName, authorSurname, year, reserved, borrowed FROM Books " +
                "WHERE title = ?";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,title);

        try(ResultSet resultSet = preparedStatement.executeQuery()){

          while(resultSet.next()) {

              books.add(mapFieldsToValue(resultSet));
          }
        }
        }
        return books;
    }

    @Override
    public List<Book> findBookByAuthor(Author author) throws SQLException {

        List<Book> books = new ArrayList<>();

        String sql = "SELECT id, title, authorName, authorSurname, year, reserved, borrowed FROM Books " +
                "WHERE authorName = ? AND authorSurname = ?";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,author.authorName() );
            preparedStatement.setString(2,author.authorSurname());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while(resultSet.next()) {

                    books.add(mapFieldsToValue(resultSet));
                }
            }
        }
        return books;
    }

    @Override
    public Optional<Book> findBookById(BookId id) throws SQLException {

        String sql = "SELECT id, title, authorName, authorSurname, year, reserved, borrowed FROM Books " +
                "WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, id.bookId().toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

               if( resultSet.next())  {

                    return Optional.of(mapFieldsToValue(resultSet));

               }else {
                   throw new SQLException("Could not find Book with id " + id.bookId());
               }
            }
        }
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {

        List<Book> books = new ArrayList<>();

        String sql ="SELECT id, title, authorName, authorSurname, year, reserved, borrowed FROM Books";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                books.add(mapFieldsToValue(resultSet));
            }
        } catch (Exception e) {

            throw new SQLException("Data retrieval failed.");
        }
        return books;
    }

    @Override
    public void insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO Books (id, title, authorName, authorSurname, year) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, book.bookId().bookId().toString());
            preparedStatement.setString(2, book.title());
            preparedStatement.setString(3, book.author().authorName());
            preparedStatement.setString(4, book.author().authorSurname());
            preparedStatement.setInt(5, book.year());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void reserveBook(BookId bookId) throws SQLException {

        findBookById(bookId);

        if(!bookFacade.isBookReserved(bookId) && !bookFacade.isBookBorrowed(bookId)) {

        String sql = "UPDATE Books SET reserved = 1 WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, bookId.bookId().toString());

            preparedStatement.executeUpdate();
        }
        }else
            throw new SQLException("Book is allready reserved or borrowed");
    }

    @Override
    public void cancelReservation(BookId bookId) throws SQLException {

        findBookById(bookId);
        if (bookFacade.isBookReserved(bookId)) {
            String sql = "UPDATE Books SET reserved = 0 WHERE id = ?";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, bookId.bookId().toString());

                preparedStatement.executeUpdate();
            }
        }else throw new SQLException("Book was not reserved");
    }

    @Override
    public void borrowBook(BookId bookId) throws SQLException {

        findBookById(bookId);

        if (!bookFacade.isBookBorrowed(bookId) && !bookFacade.isBookReserved(bookId)) {

        String sql = "UPDATE Books SET borrowed = 1 WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, bookId.bookId().toString());

            preparedStatement.executeUpdate();
        }

        }else throw new SQLException("Book is already borrowed or reserved.");
    }

    @Override
    public void returnBook(BookId bookId) throws SQLException {

        findBookById(bookId);

        if(bookFacade.isBookBorrowed(bookId)){
            String sql = "UPDATE Books SET borrowed = 0 WHERE id = ?";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, bookId.bookId().toString());

                preparedStatement.executeUpdate();
            }
        }else throw new SQLException("Book was not borrowed.");
    }
}