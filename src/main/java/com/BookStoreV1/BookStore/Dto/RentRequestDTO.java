package com.BookStoreV1.BookStore.Dto;

import com.BookStoreV1.BookStore.Model.Book;
import com.BookStoreV1.BookStore.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentRequestDTO {

    private long id;

    @NotNull
    private LocalDate data_aluguel;
    @NotNull
    private LocalDate data_previsao;

    private LocalDate data_devolucao;

    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;
}
