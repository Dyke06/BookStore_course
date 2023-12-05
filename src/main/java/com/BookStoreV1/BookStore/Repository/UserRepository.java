package com.BookStoreV1.BookStore.Repository;

import com.BookStoreV1.BookStore.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    User findById(long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);


}
