package com.BookStoreV1.BookStore.Validation.Rent;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;

import javax.persistence.EntityExistsException;

public class EstoqueEsgotadoException extends EntityExistsException {
    public EstoqueEsgotadoException(RentRequestDTO Messsage) {
        super(String.format("Estoque desse livro estar esgotado!", Messsage));
    }
}
