package com.BookStoreV1.BookStore.Validation.Book;

import javax.persistence.EntityExistsException;

public class BookHasRentsException extends EntityExistsException {
    public BookHasRentsException(Long id) {
        super(String.format("Esse livro estar associado a um aluguel!", id));
    }
}
