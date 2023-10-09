package com.BookStoreV1.BookStore.Validation.Rent;

import com.BookStoreV1.BookStore.Dto.RentUpdateDTO;

import javax.persistence.EntityExistsException;

public class DataDevolucaoActualInvalidException extends EntityExistsException {
    public DataDevolucaoActualInvalidException(RentUpdateDTO Message) {
        super(String.format("Data de devolucao não corresponde a data de hoje!", Message));
    }
}
