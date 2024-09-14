package org.san.Books;

public interface BookFactory {

    Book AddNewBook(BookId bookID, String title, Author author, BookYear year);
}
