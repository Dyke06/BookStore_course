package com.BookStoreV1.BookStore.Validation.Rent;

import javax.persistence.EntityExistsException;

public class RentUserBook extends EntityExistsException {
    public RentUserBook(){
        super("O usuário ja alugou este mesmo livro");
    }
}
