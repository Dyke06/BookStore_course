package com.BookStoreV1.BookStore.Model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String author;

    @Column(length = 4, nullable = false)
    private Integer launch;

    @Column(length = 6, nullable = false)
    private int totalRent;

    @Column(length = 6, nullable = false)
    private int amount;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Publisher publisher;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Rent> rents;

}
