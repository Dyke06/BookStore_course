package com.BookStoreV1.BookStore.Validation.Book;

import javax.persistence.EntityExistsException;

public class BookNameAlreadyExistsException extends EntityExistsException {
    public BookNameAlreadyExistsException(){
        super("Esse nome ja esta sendo usado por outro livro!");
    }
}
