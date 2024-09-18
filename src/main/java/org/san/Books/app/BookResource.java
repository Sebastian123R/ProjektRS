package org.san.Books.app;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.san.Books.Book;
import org.san.Books.BookRepository;

import java.util.List;

@Path("/books/v1")
public class BookResource {

    @Inject
    BookRepository bookRepositoryImpl;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {

        return bookRepositoryImpl.getAllBooks();
    }
}
