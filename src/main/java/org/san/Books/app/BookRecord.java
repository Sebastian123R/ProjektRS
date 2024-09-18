package org.san.Books.app;

public record BookRecord(String bookId,
                         String title,
                         String authorName,
                         String authorSurname,
                         int year) implements org.san.Books.Book {

}
