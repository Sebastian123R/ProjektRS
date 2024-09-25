package org.san.Books;

import java.sql.SQLException;

public interface BookFacade {



    public boolean isBookBorrowed(BookId bookId) throws SQLException;

    public boolean isBookReserved(BookId bookId) throws SQLException;
}
