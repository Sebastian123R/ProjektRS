package org.san.Books.app;

import org.san.Books.BookId;


 public record BookIdRecord(String bookId) implements BookId {

     @Override
     public String BookId() {
         return bookId;
     }
 }
