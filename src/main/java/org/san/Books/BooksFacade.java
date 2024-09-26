package org.san.Books;

import java.sql.SQLException;

public interface BooksFacade {

     boolean isBookBorrowed(BookId bookId) throws SQLException;

     boolean isBookReserved(BookId bookId) throws SQLException;
}
