package com.BookStoreV1.BookStore.Validation.Publisher;

import javax.persistence.EntityExistsException;

public class PublisherAlreadyExistsException extends EntityExistsException {
    public PublisherAlreadyExistsException(String name){
        super(String.format("Publisher with name %s alredy exists!", name));
    }
}
