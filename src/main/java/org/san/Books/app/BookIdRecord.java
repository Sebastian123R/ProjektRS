package org.san.Books.app;

import org.san.Books.BookId;

import java.util.UUID;


public record BookIdRecord(UUID bookId) implements BookId {
 }
