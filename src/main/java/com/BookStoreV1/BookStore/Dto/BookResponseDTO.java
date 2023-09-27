package com.BookStoreV1.BookStore.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {

    private Long id;

    private String nome;

    private String autor;

    private LocalDate lancamento;

    private Integer totalalugado;

    private Integer quantidade;

    private PublisherDTO publisher;

}
