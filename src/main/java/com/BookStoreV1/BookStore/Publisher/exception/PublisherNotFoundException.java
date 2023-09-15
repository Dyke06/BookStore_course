package com.BookStoreV1.BookStore.Publisher.exception;

import javax.persistence.EntityNotFoundException;

public class PublisherNotFoundException extends EntityNotFoundException {
    public PublisherNotFoundException(Long id) {
        super(String.format("Publisher with width id %s not exists", id));
    }
}
