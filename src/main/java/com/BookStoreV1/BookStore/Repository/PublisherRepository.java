package com.BookStoreV1.BookStore.Repository;

import com.BookStoreV1.BookStore.Model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository <Publisher, Long> {
    Optional<Publisher> findByNome(String nome);

}
