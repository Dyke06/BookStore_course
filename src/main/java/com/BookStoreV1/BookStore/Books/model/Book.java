package com.BookStoreV1.BookStore.Books.model;

import com.BookStoreV1.BookStore.Publisher.model.Publisher;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String nome;

    @Column(length = 30, nullable = false)
    private String autor;

    @Column(length = 4, nullable = false)
    private LocalDate lancamento;

    @Column(length = 6, nullable = false)
    private int totalalugado;

    @Column(length = 6, nullable = false)
    private int quantidade;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Publisher publisher;

}
