package org.san.Books.app;

import org.san.Books.Author;

 public record AuthorRecord(String authorName, String authorSurname) implements Author {
}
