package com.BookStoreV1.BookStore.Validation.Rent;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;

public class DataAluguelInvalidException extends RuntimeException {
    public DataAluguelInvalidException(RentRequestDTO message) {
        super(String.format("Data de aluguel não corresponde a data de hoje!", message));
    }
}
