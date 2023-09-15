package com.BookStoreV1.BookStore.Users.repository;

import com.BookStoreV1.BookStore.Users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    User findById(long id);
    Optional<User> findByEmail(String email);
}
