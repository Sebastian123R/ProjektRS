package org.san.Books.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.san.Books.Book;
import org.san.Books.BookFacade;
import org.san.Books.BookId;
import org.san.Books.BookRepository;

import java.sql.SQLException;
import java.util.Optional;

@ApplicationScoped
 class BookFacadeImpl implements BookFacade {

    @Inject
    BookRepository repository;

    @Override
    public boolean isBookBorrowed(BookId bookId) throws SQLException {

        Optional<Book> optionalBook = repository.findBookById(bookId);

        if (optionalBook.isPresent()) {

            Book book = optionalBook.get();
         return book.isBorrowed();
        }
        return false;
    }

    @Override
    public boolean isBookReserved(BookId bookId) throws SQLException {

        Optional<Book> optionalBook = repository.findBookById(bookId);

        if (optionalBook.isPresent()) {

            Book book = optionalBook.get();
           return book.isReserved();
        }
        return false;

    }
}
