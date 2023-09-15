package com.BookStoreV1.BookStore.Publisher.repository;

import com.BookStoreV1.BookStore.Publisher.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository <Publisher, Long> {
    Optional<Publisher> findByNome(String nome);

}
