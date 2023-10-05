package com.BookStoreV1.BookStore.Validation.Rent;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

public class DataPrevisaoInvalidException extends EntityExistsException {
    public DataPrevisaoInvalidException(RentRequestDTO Message){
        super(String.format("A data de previsão não pode ser menor que a data de aluguel.", Message));
    }
}
