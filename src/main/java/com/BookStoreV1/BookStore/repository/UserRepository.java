package com.BookStoreV1.BookStore.repository;

import com.BookStoreV1.BookStore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    User findById(long id);
}
