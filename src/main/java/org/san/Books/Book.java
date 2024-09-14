package org.san.Books;

public interface Book {
    BookId getBookId();
    String getTitle();
    Author getAuthor();
    BookYear year();
}
