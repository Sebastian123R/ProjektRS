package org.san.Books;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class BookFacade {

    private final BookRepository bookRepository;

    BookFacade(BookRepository bookRepository) {
        this.bookRepository =Objects.requireNonNull(bookRepository);
    }


    Optional<List<Book>> findBooksByAuthor(Author author) throws SQLException {
        return Optional.ofNullable(bookRepository
                .findBookByAuthor(author));
    }
}
