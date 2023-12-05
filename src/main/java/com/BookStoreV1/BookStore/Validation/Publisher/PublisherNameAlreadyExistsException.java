package com.BookStoreV1.BookStore.Validation.Publisher;

import javax.persistence.EntityExistsException;

public class PublisherNameAlreadyExistsException extends EntityExistsException {
    public PublisherNameAlreadyExistsException(){
        super("Nome ja estar sendo usado por outra editora!");
    }
}
