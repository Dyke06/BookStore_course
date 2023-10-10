package com.BookStoreV1.BookStore.Validation.Book;

import javax.persistence.EntityExistsException;

public class BookHasRentsException extends EntityExistsException {
    public BookHasRentsException(Long id) {
        super(String.format("Esse livro não pode ser deletado pois esta associado a um aluguel!", id));
    }
}
