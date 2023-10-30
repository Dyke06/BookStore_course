package com.BookStoreV1.BookStore.Validation.Book;

import com.BookStoreV1.BookStore.Dto.RequestBookDTO;

import javax.persistence.EntityExistsException;

public class BookStockAlreayException extends EntityExistsException {
    public BookStockAlreayException(RequestBookDTO Stock){
        super(String.format("O livro não pode ser criado com estoque menor ou igual a 0!", Stock));
    }
}
