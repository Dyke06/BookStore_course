package com.BookStoreV1.BookStore.Validation.Publisher;

import javax.persistence.EntityExistsException;

public class RelatedPublisherRent extends EntityExistsException {
    public RelatedPublisherRent(long id) {
        super(String.format("O A publisher não pode ser deletada pois esta associada a uma lista de livros.", id));
    }
}
