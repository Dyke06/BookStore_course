package com.BookStoreV1.BookStore.Validation.Rent;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;

public class DataDevolucaoActualInvalidException extends RuntimeException {
    public DataDevolucaoActualInvalidException(RentRequestDTO Message) {
        super(String.format("Data de devolucao não corresponde a data de hoje!", Message));
    }
}
