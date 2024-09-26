package org.san.Books.app;

import org.san.Books.Author;
import org.san.Books.BookId;

public record BookRecord(BookId bookId,
                         String title,
                         Author author,
                         int year,
                         boolean isReserved,
                         boolean isBorrowed) implements org.san.Books.Book {
}
