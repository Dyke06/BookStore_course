package com.BookStoreV1.BookStore.Repository;

import com.BookStoreV1.BookStore.Model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByUserId(Long id);

    List<Rent> findByBookId(Long id);



}
