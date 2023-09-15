package com.BookStoreV1.BookStore.Publisher.model;

import com.BookStoreV1.BookStore.Books.model.Book;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private List<Book> books;
}
