package com.BookStoreV1.BookStore.Validation.Rent;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;

import javax.persistence.EntityExistsException;

public class DataPrevisaoMonthException extends EntityExistsException {
    public DataPrevisaoMonthException(RentRequestDTO Message) {
        super(String.format("A data de previsão não exceder 1 mês após a data do aluguel.", Message));
    }
}
