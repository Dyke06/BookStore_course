package com.BookStoreV1.BookStore.Validation.Rent;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;

import javax.persistence.EntityExistsException;

public class DataAluguelInvalidException extends EntityExistsException {
    public DataAluguelInvalidException(RentRequestDTO message) {
        super(String.format("Data de aluguel não corresponde a data de hoje!", message));
    }
}
