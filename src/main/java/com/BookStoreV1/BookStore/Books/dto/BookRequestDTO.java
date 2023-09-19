package com.BookStoreV1.BookStore.Books.dto;

import com.BookStoreV1.BookStore.Publisher.model.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String nome;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String autor;

    @NotNull
    private Integer lancamento;

    @NotNull
    private Integer totalalugado;

    @NotNull
    private Integer quantidade;

    @NotNull
    private Long publisherId;
}
