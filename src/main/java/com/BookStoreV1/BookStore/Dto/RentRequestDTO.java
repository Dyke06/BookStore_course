package com.BookStoreV1.BookStore.Dto;

import com.BookStoreV1.BookStore.Model.Book;
import com.BookStoreV1.BookStore.Model.User;
import io.swagger.annotations.ApiModelProperty;
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


    @NotNull
    private LocalDate data_aluguel;
    @NotNull
    private LocalDate data_previsao;
    @ApiModelProperty(hidden = true)
    private LocalDate data_devolucao;

    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;
}
