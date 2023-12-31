package com.BookStoreV1.BookStore.Repository;

import com.BookStoreV1.BookStore.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
    Optional<Book> findById(Long id);
    List<Book> findByPublisherId(Long id);
    boolean existsByNameAndIdNot(String name, Long id);

}
