package com.BookStoreV1.BookStore.Validation.Rent;

import com.BookStoreV1.BookStore.Dto.RentUpdateDTO;

import javax.persistence.EntityExistsException;

public class ReturnDateCannotBeNull extends EntityExistsException {
    public ReturnDateCannotBeNull(RentUpdateDTO Message){
        super(String.format( "A data de devolução não pode ser NULL!", Message));
    }
}
