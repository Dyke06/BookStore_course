package com.BookStoreV1.BookStore.Validation.User;

import javax.persistence.EntityExistsException;

public class RelatedUserRent extends EntityExistsException {
    public RelatedUserRent(Long id) {
        super(String.format("Esse usuário estar relacionado a um aluguel.", id));
    }
}
