package com.BookStoreV1.BookStore.Validation.Rent;

import com.BookStoreV1.BookStore.Model.Rent;

import javax.persistence.EntityExistsException;

public class RentReturnedException extends EntityExistsException {
    public RentReturnedException(Rent existingRent) {
        super(String.format("Esse aluguel ja foi devolvido.", existingRent));
    }
}
