package com.BookStoreV1.BookStore.Model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "Rent")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate dateRent;

    @Column(nullable = false)
    private LocalDate dateForecast;

    private LocalDate dateReturn;

    @Column(length = 10, nullable = false)
    private String status;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private User user;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Book book;


}
