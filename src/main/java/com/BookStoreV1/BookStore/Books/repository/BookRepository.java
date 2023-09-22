package com.BookStoreV1.BookStore.Books.repository;

import com.BookStoreV1.BookStore.Books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByNome(String nome);

    Optional<Book> findById(Long id);
}
