package com.BookStoreV1.BookStore.repository;

import com.BookStoreV1.BookStore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    User findById(long id);
}
