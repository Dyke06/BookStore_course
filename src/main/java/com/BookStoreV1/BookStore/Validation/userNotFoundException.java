package com.BookStoreV1.BookStore.Validation;

import javax.persistence.EntityNotFoundException;

public class userNotFoundException extends EntityNotFoundException {
    public userNotFoundException(Long id) {
        super(String.format("User with %s not exist", id));
    }
}
