package com.BookStoreV1.BookStore.Validation.User;

import com.BookStoreV1.BookStore.Dto.UserDTO;

import javax.persistence.EntityExistsException;

public class EmailAlreadyExistsException extends EntityExistsException {
    public EmailAlreadyExistsException() {
        super("Email ja estar em uso por outro usuário.");
    }
}
