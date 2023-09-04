package com.BookStoreV1.BookStore.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 30, nullable = false)
    private String nome;
    @Column(length = 30, nullable = false)
    private String endereco;
    @Column(length = 30, nullable = false)
    private String cidade;
    @Column(length = 30, nullable = false, unique = true)
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
