package com.BookStoreV1.BookStore.Books.dto;

import com.BookStoreV1.BookStore.Publisher.model.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotEmpty
    @Size(max = 4)
    private Integer lancamento;

    @NotNull
    @NotEmpty
    @Size(max = 6)
    private Integer totalalugado;

    @NotNull
    @NotEmpty
    @Size(max = 6)
    private Integer quantidade;

    @NotNull
    private Long publisherId;
}
