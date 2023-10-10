package com.BookStoreV1.BookStore.Validation.Book;

import javax.persistence.EntityExistsException;

public class BookAlreadyExistsException extends EntityExistsException {
    public BookAlreadyExistsException(String nome){
        super(String.format("Livro com nome %s ja estar registrado!", nome));
    }
}
