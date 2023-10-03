package com.BookStoreV1.BookStore.Validation.User;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(Long id) {
        super(String.format("User with %s not exist", id));
    }
}