package com.BookStoreV1.BookStore.Users.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 30, nullable = false)
    private String nome;
    @Column(length = 30, nullable = false)
    private String endereco;
    @Column(length = 30, nullable = false)
    private String cidade;
    @Column(length = 30, nullable = false, unique = true)
    private String email;

}
