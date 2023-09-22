package com.BookStoreV1.BookStore.Books.exception;

import javax.persistence.EntityNotFoundException;

public class BookNotFoundExeption extends EntityNotFoundException {
    public BookNotFoundExeption(Long bookId) {
        super(String.format("Book with id %s not exists", bookId));
    }
}
