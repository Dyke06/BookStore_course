package com.BookStoreV1.BookStore.Publisher.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 30, nullable = false)
    private String nome;
    @Column(length = 30, nullable = false)
    private String cidade;
}
