package com.BookStoreV1.BookStore.Validation.Rent;

import javax.persistence.EntityNotFoundException;

public class RentNotFoundException extends EntityNotFoundException {
    public RentNotFoundException(Long rentId) {
        super(String.format("rent com id %s não existe", rentId));
    }
}
