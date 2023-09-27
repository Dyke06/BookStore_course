package com.BookStoreV1.BookStore.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

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
    private LocalDate lancamento;

    @NotNull
    private Integer totalalugado;

    @NotNull
    private Integer quantidade;

    @NotNull
    private Long publisherId;
}
