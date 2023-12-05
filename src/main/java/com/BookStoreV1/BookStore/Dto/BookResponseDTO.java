package com.BookStoreV1.BookStore.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {

    private Long id;

    private String name;

    private String author;

    private Integer launch;

    private Integer totalRent;

    private Integer amount;

    private PublisherDTO publisher;

}
