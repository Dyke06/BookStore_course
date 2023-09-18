package com.BookStoreV1.BookStore.Books.dto;

import com.BookStoreV1.BookStore.Publisher.dto.PublisherDTO;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {

    private Long id;

    private String nome;

    private String autor;

    private Integer lancamento;

    private Integer totalalugado;

    private Integer quantidade;

    private PublisherDTO publisher;

}
