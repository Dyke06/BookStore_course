package com.BookStoreV1.BookStore.Validation.Rent;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;

import javax.persistence.EntityExistsException;

public class ReturnDateCannotBeNull extends EntityExistsException {
    public ReturnDateCannotBeNull(RentRequestDTO Message){
        super(String.format( "A data de devolução não pode ser NULL!", Message));
    }
}
