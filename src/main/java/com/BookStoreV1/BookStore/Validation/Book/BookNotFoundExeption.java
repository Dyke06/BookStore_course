package com.BookStoreV1.BookStore.Validation.Book;

import javax.persistence.EntityNotFoundException;

public class BookNotFoundExeption extends EntityNotFoundException {
    public BookNotFoundExeption(Long id) {
        super(String.format("Livro com id %s não existe", id));
    }
}
