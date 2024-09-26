package org.san.Books.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.san.Books.Book;
import org.san.Books.BooksFacade;
import org.san.Books.BookId;
import org.san.Books.BooksRepository;

import java.sql.SQLException;
import java.util.Optional;

@ApplicationScoped
 public final class BooksFacadeImpl implements BooksFacade {

    @Inject
    BooksRepository repository;

    @Override
    public final boolean isBookBorrowed(BookId bookId) throws SQLException {

        Optional<Book> optionalBook = repository.findBookById(bookId);

        if (optionalBook.isPresent()) {

            Book book = optionalBook.get();
         return book.isBorrowed();
        }
        return false;
    }

    @Override
     public final boolean isBookReserved(BookId bookId) throws SQLException {

        Optional<Book> optionalBook = repository.findBookById(bookId);

        if (optionalBook.isPresent()) {

            Book book = optionalBook.get();
           return book.isReserved();
        }
        return false;

    }
}
