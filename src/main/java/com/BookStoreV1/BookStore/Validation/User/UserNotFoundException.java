package com.BookStoreV1.BookStore.Validation.User;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(Long id) {
        super(String.format("Usuário com Id %s não existe.", id));
    }
}
