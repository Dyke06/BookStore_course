package com.BookStoreV1.BookStore.Books.model;

import com.BookStoreV1.BookStore.Publisher.model.Publisher;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.*;
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
    private Integer lancamento;

    @Column(length = 6, nullable = false)
    private Integer totalalugado;

    @Column(length = 6, nullable = false)
    private Integer quantidade;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Publisher publisher;


}
