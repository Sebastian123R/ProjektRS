package org.san.Books;

public interface Book {
    BookId bookId();
    String title();
    Author author();
    int year();
    boolean isReserved();
    boolean isBorrowed();
}
