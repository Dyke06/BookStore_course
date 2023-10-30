package com.BookStoreV1.BookStore.Repository;

import com.BookStoreV1.BookStore.Model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByUserId(Long id);

    List<Rent> findByBookId(Long id);
    boolean existsByUserIdAndBookId(Long userId, Long bookId);

    @Query("SELECT COUNT(r) FROM Rent r WHERE r.status = 'Pendente'")
    long countPendentes();

    @Query("SELECT COUNT(r) FROM Rent r WHERE r.status = 'No prazo'")
    long countNoPrazo();

    @Query("SELECT COUNT(r) FROM Rent r WHERE r.status = 'Atrasado'")
    long countAtrasado();
}
