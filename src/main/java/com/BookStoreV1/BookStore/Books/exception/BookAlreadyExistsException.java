package com.BookStoreV1.BookStore.Books.exception;

import javax.persistence.EntityExistsException;

public class BookAlreadyExistsException extends EntityExistsException {
    public BookAlreadyExistsException(String nome){
        super(String.format("Book with name %s already registred!", nome));
    }
}
