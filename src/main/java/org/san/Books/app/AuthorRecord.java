package org.san.Books.app;

import org.san.Books.Author;

public record AuthorRecord(String authorName, String authorSurame) implements Author {

    @Override
    public String authorName() {
        return authorName;
    }

    @Override
    public String authorSurname() {
        return authorSurame;
    }
}
