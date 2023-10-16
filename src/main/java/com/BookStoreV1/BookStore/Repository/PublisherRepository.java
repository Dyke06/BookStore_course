package com.BookStoreV1.BookStore.Repository;

import com.BookStoreV1.BookStore.Model.Publisher;
import com.BookStoreV1.BookStore.Model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends JpaRepository <Publisher, Long> {
    Optional<Publisher> findByName(String name);

}
