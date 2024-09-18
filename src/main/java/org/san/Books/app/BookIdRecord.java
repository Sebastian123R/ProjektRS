package org.san.Books.app;

import org.san.Books.BookId;

import java.util.UUID;

 record BookIdRecord() implements BookId {

     @Override
    public String createId() {
        return UUID.randomUUID().toString();
    }
}
