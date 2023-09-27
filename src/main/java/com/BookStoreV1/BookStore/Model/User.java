package com.BookStoreV1.BookStore.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Rent> rents;

}
