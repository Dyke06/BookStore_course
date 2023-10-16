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
import java.time.ZoneId;

@Data
@AllArgsConstructor
public class RentRequestDTO {
    @ApiModelProperty(hidden = true)
    private LocalDate dateRent;

    @NotNull
    private LocalDate dateForecast;

    @ApiModelProperty(hidden = true)
    private LocalDate dateReturn;

    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;


}
