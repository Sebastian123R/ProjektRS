package org.san.Books;

public interface Book {
    BookId BookId();
    String Title();
    Author Author();
    int year();
    boolean isReserved();
    boolean isBorrowed();
}
