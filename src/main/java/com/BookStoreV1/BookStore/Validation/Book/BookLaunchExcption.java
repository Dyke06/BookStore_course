package com.BookStoreV1.BookStore.Validation.Book;

import com.BookStoreV1.BookStore.Dto.RequestBookDTO;

import javax.persistence.EntityExistsException;

public class BookLaunchExcption extends EntityExistsException {
    public BookLaunchExcption(RequestBookDTO anoInválido) {
        super(String.format("Ano é invalido", anoInválido));
    }
}
