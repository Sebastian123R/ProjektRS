package org.san.Books;

import java.util.List;

public interface BookRepository {

    List<Book> getBookByTitle(String title);

    List<Book> findBookByAuthor(Author author);

    List<Book> getAllBooks();

    List<Book> sortByTitle(List<Book> books);
}
