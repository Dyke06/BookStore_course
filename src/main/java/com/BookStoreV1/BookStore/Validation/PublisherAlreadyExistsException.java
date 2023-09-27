package com.BookStoreV1.BookStore.Validation;

import javax.persistence.EntityExistsException;

public class PublisherAlreadyExistsException extends EntityExistsException {
    public PublisherAlreadyExistsException(String name){
        super(String.format("Publisher with name %s alredy exists!", name));
    }
}
