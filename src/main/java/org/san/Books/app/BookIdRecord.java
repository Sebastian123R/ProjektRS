package org.san.Books.app;

import org.san.Books.BookId;


 public record BookIdRecord(String bookId) implements BookId {

     @Override
     public String bookId() {
         return bookId;
     }
 }
