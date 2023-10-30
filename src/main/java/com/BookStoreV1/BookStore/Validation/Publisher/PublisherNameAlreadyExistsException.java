package com.BookStoreV1.BookStore.Validation.Publisher;

import javax.persistence.EntityExistsException;

public class PublisherNameAlreadyExistsException extends EntityExistsException {
    public PublisherNameAlreadyExistsException(){
        super("Esse nome ja esta sendo usado por outra editora!");
    }
}
