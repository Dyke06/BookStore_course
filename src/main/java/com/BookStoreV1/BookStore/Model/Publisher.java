package com.BookStoreV1.BookStore.Model;

import com.BookStoreV1.BookStore.Model.Book;
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
    private String name;

    @Column(length = 30, nullable = false)
    private String city;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private List<Book> books;
}
