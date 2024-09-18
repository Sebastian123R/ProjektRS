package org.san.Books;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class BookFacade {

    private final BookRepository bookRepository;

    BookFacade(BookRepository bookRepository) {
        this.bookRepository =Objects.requireNonNull(bookRepository);
    }

    Optional<List<Book>> findBook(String title){
        return Optional.ofNullable(bookRepository
                .getBookByTitle(title));
    }

    Optional<List<Book>> findBooksByAuthor(Author author){
        return Optional.ofNullable(bookRepository
                .findBookByAuthor(author));
    }
}
