package com.BookStoreV1.BookStore.Validation;

import javax.persistence.EntityExistsException;

public class UserAlreadExistsException extends EntityExistsException {
    public UserAlreadExistsException(String email) {
        super(String.format("Email ja estar cadastrado", email ));
    }
}
