package com.BookStoreV1.BookStore.Validation.Publisher;

import javax.persistence.EntityExistsException;

public class RelatedPublisherRent extends EntityExistsException {
    public RelatedPublisherRent(long id) {
        super(String.format("A editora estar associada a um livro.", id));
    }
}
