package org.san.Books.app;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.san.Books.Author;
import org.san.Books.Book;
import org.san.Books.BookRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Path("/books/v1")
public class BookResource {

    @Inject
    BookRepository bookRepositoryImpl;

    @GET
    @Path("/allbooks")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {

        return bookRepositoryImpl.getAllBooks();
    }

    @GET
    @Path("/author")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getByAuthor(
            @QueryParam("authorName") String authorName,
            @QueryParam("authorSurname") String authorSurname) throws SQLException {
        return bookRepositoryImpl.findBookByAuthor(new AuthorRecord(authorName,authorSurname));
    }

    @GET
    @Path("/Title")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getByTitle(@QueryParam("title") String title) throws SQLException {
    return bookRepositoryImpl.getBookByTitle(title);
    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertBook(BookDTO bookDTO) throws SQLException {
        Author author = new AuthorRecord(bookDTO.authorName(), bookDTO.authorSurname());
        Book book = new BookRecord(new BookIdRecord(UUID.randomUUID().toString()), bookDTO.title(), author, bookDTO.year());
        bookRepositoryImpl.insertBook(book);
        return Response.status(Response.Status.CREATED).build();
    }
}
