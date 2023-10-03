package com.BookStoreV1.BookStore.Validation.Publisher;

import javax.persistence.EntityNotFoundException;

public class PublisherNotFoundException extends EntityNotFoundException {
    public PublisherNotFoundException(Long id) {
        super(String.format("Publisher with width id %s not exists", id));
    }
}
