package org.san.Books.app;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.san.Books.Author;
import org.san.Books.Book;
import org.san.Books.BookId;
import org.san.Books.BookRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/books/v1")
public class BookResource {

    @Inject
    BookRepository bookRepositoryImpl;

    @GET
    @Path("/getAllBooks")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() throws SQLException {

        return bookRepositoryImpl.getAllBooks();
    }
    @GET
    @Path("/findById")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Book> findBookById(@QueryParam("id")String bookId) throws SQLException {
        BookId bookIdRecord = new BookIdRecord(UUID.fromString(bookId));
        return bookRepositoryImpl.findBookById(bookIdRecord);
    }

    @GET
    @Path("/findByAuthor")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getByAuthor(
            @QueryParam("authorName") String authorName,
            @QueryParam("authorSurname") String authorSurname) throws SQLException {
        return bookRepositoryImpl.findBookByAuthor(new AuthorRecord(authorName, authorSurname));
    }

    @GET
    @Path("/findByTitle")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getByTitle(@QueryParam("title") String title) throws SQLException {
        return bookRepositoryImpl.getBookByTitle(title);
    }

    @POST
    @Path("/insertBook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertBook(BookDTO bookDTO) throws SQLException {
        Author author = new AuthorRecord(bookDTO.authorName(), bookDTO.authorSurname());
        Book book = new BookRecord(new BookIdRecord(UUID.randomUUID()), bookDTO.title(), author, bookDTO.year(), false, false);
        bookRepositoryImpl.insertBook(book);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/reserveBook")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reserveBook(@QueryParam("id") String id) throws SQLException {

           BookId bookIdRecord = new BookIdRecord(UUID.fromString(id));
           bookRepositoryImpl.reserveBook(bookIdRecord);
           return Response.ok("Book id: " + bookIdRecord + "reserved").build();

    }
    @PUT
    @Path("/cancelReserve")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelReserveBook(@QueryParam("id") String id) throws SQLException {
            BookId bookIdRecord = new BookIdRecord(UUID.fromString(id));
            bookRepositoryImpl.cancelReservation(bookIdRecord);
            return Response.ok("Book id: " + bookIdRecord + "canceled reservation").build();
    }

    @PUT
    @Path("/borrowBook")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrowBook(@QueryParam("id") String id) throws SQLException {

            BookId bookIdRecord = new BookIdRecord(UUID.fromString(id));
            bookRepositoryImpl.borrowBook(bookIdRecord);
            return Response.ok("Book id: " + bookIdRecord + "borrowed").build();
    }

    @PUT
    @Path("/returnBook")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnBook(@QueryParam("id") String id) throws SQLException {
            BookId bookIdRecord = new BookIdRecord(UUID.fromString(id));
            bookRepositoryImpl.returnBook(bookIdRecord);
            return Response.ok("Book id: " + bookIdRecord + "returned").build();
    }
}